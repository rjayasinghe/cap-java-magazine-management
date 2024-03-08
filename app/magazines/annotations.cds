using acme.magazine.service.MagazineCatalogService as service from '../../srv/magazineCatalogService';

annotate service.Issues with @(
    UI.LineItem : [
        {
            $Type : 'UI.DataField',
            Label : 'IssueNo',
            Value : IssueNo,
        },
        {
            $Type : 'UI.DataField',
            Label : 'ReleaseDate',
            Value : ReleaseDate,
        },
        {
            $Type : 'UI.DataField',
            Label : 'FocusTopic',
            Value : FocusTopic,
        },
    ]
);
annotate service.Issues with @(
    UI.FieldGroup #GeneratedGroup1 : {
        $Type : 'UI.FieldGroupType',
        Data : [
            {
                $Type : 'UI.DataField',
                Label : 'IssueNo',
                Value : IssueNo,
            },
            {
                $Type : 'UI.DataField',
                Label : 'ReleaseDate',
                Value : ReleaseDate,
            },
            {
                $Type : 'UI.DataField',
                Label : 'FocusTopic',
                Value : FocusTopic,
            },
        ],
    },
    UI.Facets : [
        {
            $Type : 'UI.ReferenceFacet',
            ID : 'GeneratedFacet1',
            Label : 'General Information',
            Target : '@UI.FieldGroup#GeneratedGroup1',
        },
    ]
);
