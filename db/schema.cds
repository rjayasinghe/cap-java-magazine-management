namespace acme.magazine;

entity Issues {
    key Id : UUID;
    IssueNo : Integer;
    ReleaseDate : Date;
    FocusTopic : String;
}

entity Articles {
    key Id : UUID;
    Title : String;
    Author : String;
    TextBody : LargeString
}

entity Authors {
    key Id : UUID;
    FirstName : String;
    LastName : String;
    EMail : String;
    Photo : LargeBinary;
}
