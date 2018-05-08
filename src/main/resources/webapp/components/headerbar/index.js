define(["text!./template.html"],function(template){
	return {
		template:template,
		data:function(){
			return {
				showMenu:true
			}
		}
	}
});