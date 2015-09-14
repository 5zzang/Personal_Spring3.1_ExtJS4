Ext.define('app.view.Viewport', {
	extend: 'Ext.container.Viewport',
	layout: 'border',
	items: [{
		region: 'north',
		xtype: 'panel',
		height: 70 
	}, {
		region: 'west',
		xtype: 'NavigationPanel',
		width: 200
	}, {
		region: 'east',
		xtype: 'SidePanel',
		width: 200
	}, {
		region: 'south',
		xtype: 'panel',
		height: 30
	}, {
		region: 'center',
		xtype: 'tabpanel',
	}]
});