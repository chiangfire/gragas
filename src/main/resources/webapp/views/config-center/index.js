define([ "text!./template.html" ], function(template) {
	return {
		template : template,
		data : function() {
			var $this = this;
			//验证Name
            var validatorName = function(rule, value, callback){
            	if(!value){
            		return callback(new Error("Name 不能为空"));
            	}
            	$this.node.childrens.map(function(currentValue){
            		if(currentValue.name === value){
            			return callback(new Error("Name 已存在"));
            		}
            	});
            	if(/\W|[\u4e00-\u9fa5]/g.test(value)){
            		return callback("Name 不能包含空格且不能含有中文字符");
            	}
            	return callback();
            };
			return {
				node:{
					name:"测试数据",
					childrens:[],
					propertys:[]
				},
				propertyModal: false,
				nodeModal:false,
				loading:true,
				nodeForm:{},
                propertyForm:{},
                checkPropertys:[],
				propertyColumns:[{
					type : 'selection',
					width : 32,
					key : "ss"
				}, {
					title : 'Name',
					key : 'name'
				}, {
					title : 'Value',
					key : 'value'
				}],
				ruleValidate: {
                    name: [
                        { required: true,trigger:'blur',validator:validatorName}
                    ],
                    value: [
                        { required: true, message: 'Value 不能为空', trigger: 'blur' }
                    ]
                }
			}
		},
    	created:function(){
    		this.nodeNames = [this.node.name];
    	},
        methods: {
            append:function(formName,modalName) {
            	var $this = this;
            	this.$refs[formName].validate(function(valid){
            		if(valid){
            			$this[modalName] = false;
            			//添加节点
            			if(formName === "nodeForm" && modalName === "nodeModal"){
                            $this.node.childrens.push({
                                name: $this.nodeForm.name
                            });
            			}
            			//添加属性
            			if(formName === "propertyForm" && modalName === "propertyModal"){
            				$this.node.propertys.push({
            					name:$this.propertyForm.name,
            					value:$this.propertyForm.value
            				});
            			}
                        //this.$set(node, 'children', children);  如果添加新属性需调用set进行数据绑定，否则界面不会刷新
                        $this.cancelForm(formName);
            		}
            		$this.loading = false;
                    $this.$nextTick(() => {
                        $this.loading = true;
                    });
            	});
            },
            remove:function(node) {
            	//删除节点
            	if(node){
                    var index = this.node.childrens.indexOf(node);
                    this.node.childrens.splice(index, 1);
                //删除属性    
            	}else{
            		var $this = this;
            		$this.node.propertys.forEach(function(proper,i){
            			$this.checkPropertys.forEach(function(item,j){
            				if(proper.constructor === item.constructor){
            					$this.node.propertys.splice(i,1);
            					$this.checkPropertys.splice(j,1);
            				}
            			});
            		});
            	}
            },
            selectionChange:function(sel){
            	this.checkPropertys = sel;
            },
            updateProperty:function(){
            	this.propertyModal = true;
        		this.propertyForm.name = this.checkPropertys[0].name;
        		this.propertyForm.value = this.checkPropertys[0].value;
            },
            rowClick:function(item,index){
            	this.$refs.propertySelection.toggleSelect(index)
            },
            linkNode:function(name){
            	var position = this.nodeNames.indexOf(name);
            	//不是最后一个
            	if(position != -1 && position != this.nodeNames.length - 1){
            		//发送请求获取数据
            	}
            },
            cancelForm:function(formName){
            	this.$refs[formName].resetFields();
            	var checkPropertysLength = this.checkPropertys.length;
            	if(checkPropertysLength > 0){
            		this.checkPropertys.splice(0,checkPropertysLength);
            	}
            }
        }
	}
});