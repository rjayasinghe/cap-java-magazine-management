sap.ui.require(
    [
        'sap/fe/test/JourneyRunner',
        'com/sap/cds/magazines/test/integration/FirstJourney',
		'com/sap/cds/magazines/test/integration/pages/IssuesList',
		'com/sap/cds/magazines/test/integration/pages/IssuesObjectPage'
    ],
    function(JourneyRunner, opaJourney, IssuesList, IssuesObjectPage) {
        'use strict';
        var JourneyRunner = new JourneyRunner({
            // start index.html in web folder
            launchUrl: sap.ui.require.toUrl('com/sap/cds/magazines') + '/index.html'
        });

       
        JourneyRunner.run(
            {
                pages: { 
					onTheIssuesList: IssuesList,
					onTheIssuesObjectPage: IssuesObjectPage
                }
            },
            opaJourney.run
        );
    }
);