namespace acme.magazine;

entity Issues {
    key Id          : UUID;
        IssueNo     : Integer;
        ReleaseDate : Date;
        FocusTopic  : String;
        IsPublished : Boolean;
        Articles : Composition of many Articles;
};

entity Articles {
    key Id : UUID;
    Title : String;
    TextBody : LargeString;
    Author : Association to one Authors;
}

entity Authors {
    key Id : UUID;
    FirstName : String;
    LastName : String;
    EMail : String;
    Photo : LargeBinary;
}
