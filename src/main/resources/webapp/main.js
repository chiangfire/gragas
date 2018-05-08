require.config({
    baseUrl:"/w",
    paths: {
        'text': 'assets/js/text',
	    'vue' : 'assets/js/vue',
	    'iview': 'assets/js/iview.min',
	    'vue-router':'assets/js/vue-router.min'
    },
    deps : ['main'] //依赖加载完成执行当前模块
});
define("main",function(require) {
	var text = require("text");
	var Vue = require("vue");
	var iview = require("iview");
	Vue.use(iview);
	Vue.component("headerbar",require("components/headerbar/index"));
	Vue.component("collapse-transition",require("components/collapse-transition"));
	
	new Vue({
		el:"#gragas-app",
		router:require("routes/index"),
		data:{
            appFrame:{
                xs:24,
                lg:{span:16,offset:4}
            }
        }
	});
});