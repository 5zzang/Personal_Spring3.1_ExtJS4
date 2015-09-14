Ext.define('app.view.frame.NavigationPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.NavigationPanel',
	layout: 'accordion',
	collapsible: true,
	split: true,
	title: 'Navigation',
	activeItem: 0,
	initComponent: function() {
		this.callParent(arguments);
	}
});