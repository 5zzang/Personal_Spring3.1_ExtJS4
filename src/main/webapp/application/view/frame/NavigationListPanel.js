Ext.define('app.view.frame.NavigationListPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.NavigationListPanel',
	animCollapse: true,
	collapsible: true,
	collapsed: true,
	useArrows: true,
	rootVisible: false,
	store: this.store,
	multiSelect: false,
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				xtype: 'dataview',
				trackOver: true,
				store: this.store,
				cls: 'feed-list',
				itemSelector: '.feed-list-item',
				overItemCls: 'feed-list-item-hover',
				tpl: '<tpl for="."><div class="feed-list-item {pgm_icon}">{pgm_nm}</div></tpl><p>'
			}],
			header: {
				toolFirst: true
			}
		});
		
		this.callParent(arguments);
	}
});