package com.acme.magazine.issues.handlers.issues;

import cds.gen.acme.magazine.service.magazinecatalogservice.Articles;
import cds.gen.acme.magazine.service.magazinecatalogservice.AuthorInvoiceIssued;
import cds.gen.acme.magazine.service.magazinecatalogservice.AuthorInvoiceIssuedContext;
import cds.gen.acme.magazine.service.magazinecatalogservice.CreateInvoicesContext;
import cds.gen.acme.magazine.service.magazinecatalogservice.Issues;
import cds.gen.acme.magazine.service.magazinecatalogservice.MagazineCatalogService;
import cds.gen.acme.magazine.service.magazinecatalogservice.MagazineCatalogService_;
import com.sap.cds.ql.CQL;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnSelectListItem;
import com.sap.cds.ql.cqn.Modifier;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ServiceName(MagazineCatalogService_.CDS_NAME)
public class CreateInvoicesHandler implements EventHandler {
    private final MagazineCatalogService service;
    private static final Logger logger = LoggerFactory.getLogger(CreateInvoicesHandler.class);

    public CreateInvoicesHandler(MagazineCatalogService service) {
        this.service = service;
    }

    @On
    public void onCreateInvoice(CreateInvoicesContext ctx) {

        //add expands to articles and authors to get relevant data for invoice
        CqnSelect cqnSelectWith = CQL.copy(ctx.getCqn(), new Modifier() {
            @Override
            public List<CqnSelectListItem> items(List<CqnSelectListItem> items) {
                items.add(CQL.to(Issues.ARTICLES).expand(a -> a.to(Articles.AUTHOR).expand(), a -> a.get(Articles.TITLE)));
                return Modifier.super.items(items);
            }
        });

        Issues issue = service.run(cqnSelectWith).first(Issues.class).orElseThrow(IllegalArgumentException::new);

        for(Articles article : issue.getArticles()) {
            //create invoice event for the article and author (author will potentially get more than one invoice)
            AuthorInvoiceIssued authorInvoiceIssuedEvent = AuthorInvoiceIssued.create();
            authorInvoiceIssuedEvent.setTitle(article.getTitle());
            authorInvoiceIssuedEvent.setName(article.getAuthor().getFirstName().concat(" ").concat(article.getAuthor().getLastName()));
            authorInvoiceIssuedEvent.setEmail(article.getAuthor().getEMail());

            // emit the event (currently there is no handler)
            var eventContext = AuthorInvoiceIssuedContext.create();
            eventContext.setData(authorInvoiceIssuedEvent);
            service.emit(eventContext);
            logger.info("Created and emitted an AuthorInvoiceIssued event {}", eventContext.getData());
        }
        ctx.setCompleted();
    }
}
