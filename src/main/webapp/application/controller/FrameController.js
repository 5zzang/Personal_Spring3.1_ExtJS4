Ext.define('app.controller.FrameController', {
	extend: 'Ext.app.Controller',
	stores: ['navigation.ProgramStore'],
	views: [
		'app.view.frame.NavigationPanel',
		'app.view.frame.NavigationListPanel',
		'app.view.frame.SidePanel'
	],
	refs: [{
		ref: 'navigationList',
		selector: 'NavigationPanel'
	}],
	init: function() {
		this.control({
			'NavigationPanel > NavigationListPanel' : {
				afterrender: this.firstSelect,
				expand: this.onItemClicked
			}
		});
	},
	firstSelect: function(panel) {
		console.log('추가된 시스템 패널', panel.xtype, panel.title, panel.collapsed);
		
		if (!panel.collapsed) {
			console.log('추가된 시스템 패널 중 접히지 않은 패널 : ', panel.title, panel.collapsed);
		}
	},
	onItemClicked: function(a, b, c) {
		console.log(a, b, c);
	},
	onLaunch: function() {
		var list = this.getNavigationList();
		var store = this.getStore('navigation.ProgramStore');
		
		store.load(function(record, b, c) {
			store.each(function(rec) {
				list.add({
					xtype: 'NavigationListPanel',
					title: rec.get('pgm_sysnm'),
					fnc_pgm_syscd: rec.get('pgm_syscd'),
					iconCls: rec.get('pgm_sysicon')
				});
			});
		});
	}
});