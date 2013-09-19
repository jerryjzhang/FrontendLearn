Ext.define('ExtjsProject.model.RequestHistory', {
    extend: 'Ext.data.Model',
    fields: [
        {
            name: 'name',
            type: 'string'
        },
        {
            name: 'updateDatetime',
            type: 'string'
        },
        {
            name: 'sourceDbName',
            type: 'string'
        },
        {
            name: 'targetDbName',
            type: 'string'
        },
        {
            name: 'status',
            type: 'string'
        },
        {
            name: 'refreshSource',
            type: 'string'
        },
    ]
});