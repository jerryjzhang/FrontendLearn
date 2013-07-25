/*!
 * Ext JS Library 3.4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.onReady(function(){
	Ext.QuickTips.init();

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.    
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    function change(val) {
        if (val > 0) {
            return '<span style="color:green;">' + val + '</span>';
        } else if (val < 0) {
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    function pctChange(val) {
        if (val > 0) {
            return '<span style="color:green;">' + val + '%</span>';
        } else if (val < 0) {
            return '<span style="color:red;">' + val + '%</span>';
        }
        return val;
    }

    // create the data store
    var store = new Ext.data.JsonStore({
        fields: [
           {name: 'id'},
           {name: 'name'},
        ]
    });

    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {
                id       :'id',
                header   : 'Id', 
                width    : 40, 
                sortable : true, 
                dataIndex: 'id'
            },
            {
                header   : 'Name', 
                width    : 80, 
                sortable : true, 
                dataIndex: 'name'
            },
            {
                xtype: 'actioncolumn',
                width: 50,
            }
        ],
        stripeRows: true,
        autoExpandColumn: 'id',
        height: 350,
        width: 600,
        title: 'Array Grid',
        // config options for stateful behavior
        stateful: true,
        stateId: 'grid'
    });

    // render the grid to the specified div in the page
    grid.render('grid-example');

	Ext.Ajax.request({
        url: 'alcazar/requests/get/all',
        method : 'GET',
        success: function(response, opts) {
           var jsonObjs = Ext.decode(response.responseText);
           for(var i = 0; i < jsonObjs.length; ++i){
        	   //alert(jsonObjs[i].name);
           }

           store.loadData(jsonObjs);
        },
        failure: function(response, opts) {
        	alert('server-side failure with status code ' + response.status);
        }
     });
	
	
});