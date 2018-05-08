define(function(require){
	var Vue = require("vue");
	var VueRouter = require("vue-router");
	Vue.use(VueRouter);
	
	return new VueRouter({
		routes:[{
			path:"/config",
			component:Vue.extend(require("views/config-center/index"))
		}]
	});
});