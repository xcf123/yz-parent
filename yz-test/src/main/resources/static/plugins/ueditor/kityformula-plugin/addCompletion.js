UE.plugins['completion'] = function(){ 
	var me=this;
	
	me.commands['completion']={
			execCommand:function(cmdName,myobject){
				//this.execCommand('insertHtml', '<completion>(___)</completion>');
				var iconUrl = me.options.UEDITOR_HOME_URL + "completion.png";
				this.execCommand('insertHtml', '<img src="'+iconUrl+'" name="completion" />');
			},
			queryCommandState: function() { 
				
			}
	};
	 
};
