Ext.define('app.view.frame.SidePanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.SidePanel',
	collapsible: true,
	split: true,
	title: 'SideBar',
	initComponent: function() {
		this.callParent(arguments);
	}
});