
using {acme.magazine as domain} from `../db/schema`;

namespace acme.magazine.service;

service MagazineCatalogService {
    @odata.draft.enabled
    entity Issues as projection on domain.Issues;
}
