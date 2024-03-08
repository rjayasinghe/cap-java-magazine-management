sap.ui.define(['sap/fe/test/ObjectPage'], function(ObjectPage) {
    'use strict';

    var CustomPageDefinitions = {
        actions: {},
        assertions: {}
    };

    return new ObjectPage(
        {
            appId: 'com.sap.cds.magazines',
            componentId: 'IssuesObjectPage',
            contextPath: '/Issues'
        },
        CustomPageDefinitions
    );
});