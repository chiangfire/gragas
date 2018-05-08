define(["./dom"],function(dom){
	return {
	    functional: true,
	    render(h, { children }) {
	        var data = {
	            on:{
		    	    afterLeave:function(el){
		    	    	dom.removeClass(el, 'collapse-transition');
		    		    el.style.height = '';
		    		    el.style.overflow = el.dataset.oldOverflow;
		    		    el.style.paddingTop = el.dataset.oldPaddingTop;
		    		    el.style.paddingBottom = el.dataset.oldPaddingBottom;
	    	        },
	    	        leave:function(el){
		    		    if (el.scrollHeight !== 0) {
		    		    	dom.addClass(el, 'collapse-transition');
		    		        el.style.height = 0;
		    		        el.style.paddingTop = 0;
		    		        el.style.paddingBottom = 0;
		    		    }
	    	        },
	    	        beforeLeave:function(el){
		    		    if (!el.dataset) el.dataset = {};
		    		    el.dataset.oldPaddingTop = el.style.paddingTop;
		    		    el.dataset.oldPaddingBottom = el.style.paddingBottom;
		    		    el.dataset.oldOverflow = el.style.overflow;
		
		    		    el.style.height = el.scrollHeight + 'px';
		    		    el.style.overflow = 'hidden';
	    	        },
	    	        afterEnter:function(el){
	    	        	dom.removeClass(el, 'collapse-transition');
		    		    el.style.height = '';
		    		    el.style.overflow = el.dataset.oldOverflow;
	    	        },
	    	        enter:function(el){
		    		    el.dataset.oldOverflow = el.style.overflow;
		    		    if (el.scrollHeight !== 0) {
		    		        el.style.height = el.scrollHeight + 'px';
		    		        el.style.paddingTop = el.dataset.oldPaddingTop;
		    		        el.style.paddingBottom = el.dataset.oldPaddingBottom;
		    		    } else {
		    		        el.style.height = '';
		    		        el.style.paddingTop = el.dataset.oldPaddingTop;
		    		        el.style.paddingBottom = el.dataset.oldPaddingBottom;
		    		    }
		
		    		    el.style.overflow = 'hidden';
	    	        },
	    	        beforeEnter:function(el){
	    	        	dom.addClass(el, 'collapse-transition');
		    		    if (!el.dataset) el.dataset = {};
		
		    		    el.dataset.oldPaddingTop = el.style.paddingTop;
		    		    el.dataset.oldPaddingBottom = el.style.paddingBottom;
		
		    		    el.style.height = '0';
		    		    el.style.paddingTop = 0;
		    		    el.style.paddingBottom = 0;
	    	        }
	                	  
	            }
	        };
	        return h('transition', data, children);
	    }
	};
});