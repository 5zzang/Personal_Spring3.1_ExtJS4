Ext.define('app.store.navigation.ProgramStore', {
	extend: 'Ext.data.Store',
	autoLoad: false,
	model: 'app.model.navigation.ProgramModel',
	proxy: {
		type: 'ajax',
		url: '/json/systemList.json',
		reader: {
			type: 'json',
			root: 'entitys',
			totalProperty: 'totalCount',
			messageProperty: 'message'
		},
		listeners: {
			exception: function(proxy, response, operation) {
				
			}
		}
	}
});