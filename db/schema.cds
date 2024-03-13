namespace acme.magazine;

entity Issues {
    key Id          : UUID;
        IssueNo     : Integer;
        ReleaseDate : Date;
        FocusTopic  : String;
        IsPublished : Boolean;
        Articles : Composition of many Articles on Articles.Issue = $self;
};

@cds.autoexpose
entity Articles {
    key Id : UUID;
    Title : String;
    TextBody : LargeString;
    Issue : Association to one Issues;
    Author : Association to one Authors;
}

@cds.autoexpose
entity Authors {
    key Id : UUID;
    FirstName : String;
    LastName : String;
    EMail : String;
    Photo : LargeBinary;
}