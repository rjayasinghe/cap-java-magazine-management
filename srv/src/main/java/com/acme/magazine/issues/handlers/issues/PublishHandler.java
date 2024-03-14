package com.acme.magazine.issues.handlers.issues;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.Update;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.acme.magazine.service.magazinecatalogservice.Issues;
import cds.gen.acme.magazine.service.magazinecatalogservice.Issues_;
import cds.gen.acme.magazine.service.magazinecatalogservice.MagazineCatalogService;
import cds.gen.acme.magazine.service.magazinecatalogservice.MagazineCatalogService_;
import cds.gen.acme.magazine.service.magazinecatalogservice.PublishContext;

@Component
@ServiceName(MagazineCatalogService_.CDS_NAME)
public class PublishHandler implements EventHandler {

    private final MagazineCatalogService service;

    public PublishHandler(MagazineCatalogService service) {
        this.service = service;
    }

    @On
    public void onPublish(PublishContext ctx) {
        
        Issues issue = service.run(ctx.getCqn()).first().map(r -> r.as(Issues.class)).orElseThrow(IllegalArgumentException::new);
        issue.setIsPublished(true);
        service.run(Update.entity(Issues_.CDS_NAME).data(issue));

        ctx.setCompleted();
    }
}
