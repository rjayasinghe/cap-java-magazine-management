using {acme.magazine as domain} from`../db/schema`;

namespace acme.magazine.service;

service MagazineCatalogService {
    entity Issues as projection on domain.Issues actions {
        action publish();
        action createInvoices();
    };

    event AuthorInvoiceIssued{
        Name : String;
        Email : String;
        Title : String;
    };
}
