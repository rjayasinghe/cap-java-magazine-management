
using {acme.magazine as domain} from `../db/schema`;

namespace acme.magazine.service;

service MagazineCatalogService {
    entity Issues as projection on domain.Issues actions {
                         action publish()
                     };
}
