Ext.define('app.model.navigation.ProgramModel', {
	extend: 'Ext.data.Model',
	fields: [
	        'pgm_id',
	        'pgm_nm',
	        'pgm_syscd',
	        'pgm_sysnm',
	        'pgm_class',
	        'pgm_icon',
	        'pgm_sysicon',
	        'group_id',
	        'group_nm',
	        'group_status_cd',
	        'group_status_nm',
	        'group_pgm_status_nm'
	]
});