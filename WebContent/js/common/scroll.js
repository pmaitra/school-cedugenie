(function($){
			$(window).load(function(){
				$("#content_1").mCustomScrollbar({
					set_width:false, /*optional element width: boolean, pixels, percentage*/
					set_height:false, /*optional element height: boolean, pixels, percentage*/
					horizontalScroll:false, /*scroll horizontally: boolean*/
					scrollInertia:550, /*scrolling inertia: integer (milliseconds)*/
					scrollEasing:"easeOutCirc", /*scrolling easing: string*/
					mouseWheel:"pixels", /*mousewheel support and velocity: boolean, "auto", integer, "pixels"*/
					mouseWheelPixels:60, /*mousewheel pixels amount: integer*/
					autoDraggerLength:true, /*auto-adjust scrollbar dragger length: boolean*/
					scrollButtons:{ /*scroll buttons*/
						enable:false, /*scroll buttons support: boolean*/
						scrollType:"continuous", /*scroll buttons scrolling type: "continuous", "pixels"*/
						scrollSpeed:20, /*scroll buttons continuous scrolling speed: integer*/
						scrollAmount:40 /*scroll buttons pixels scroll amount: integer (pixels)*/
					},
					advanced:{
						updateOnBrowserResize:true, /*update scrollbars on browser resize (for layouts based on percentages): boolean*/
						updateOnContentResize:false, /*auto-update scrollbars on content resize (for dynamic content): boolean*/
						autoExpandHorizontalScroll:false, /*auto expand width for horizontal scrolling: boolean*/
						autoScrollOnFocus:true /*auto-scroll on focused elements: boolean*/
					},
					callbacks:{
						onScrollStart:function(){}, /*user custom callback function on scroll start event*/
						onScroll:function(){}, /*user custom callback function on scroll event*/
						onTotalScroll:function(){}, /*user custom callback function on scroll end reached event*/
						onTotalScrollBack:function(){}, /*user custom callback function on scroll begin reached event*/
						onTotalScrollOffset:0, /*scroll end reached offset: integer (pixels)*/
						whileScrolling:false, /*user custom callback function on scrolling event*/
						whileScrollingInterval:30 /*interval for calling whileScrolling callback: integer (milliseconds)*/
					}
				});
			});
		})(jQuery);
		
		
(function($){
			$(window).load(function(){
				$("#content_2").mCustomScrollbar({
					set_width:false, /*optional element width: boolean, pixels, percentage*/
					set_height:false, /*optional element height: boolean, pixels, percentage*/
					horizontalScroll:false, /*scroll horizontally: boolean*/
					scrollInertia:550, /*scrolling inertia: integer (milliseconds)*/
					scrollEasing:"easeOutCirc", /*scrolling easing: string*/
					mouseWheel:"pixels", /*mousewheel support and velocity: boolean, "auto", integer, "pixels"*/
					mouseWheelPixels:60, /*mousewheel pixels amount: integer*/
					autoDraggerLength:true, /*auto-adjust scrollbar dragger length: boolean*/
					scrollButtons:{ /*scroll buttons*/
						enable:false, /*scroll buttons support: boolean*/
						scrollType:"continuous", /*scroll buttons scrolling type: "continuous", "pixels"*/
						scrollSpeed:20, /*scroll buttons continuous scrolling speed: integer*/
						scrollAmount:40 /*scroll buttons pixels scroll amount: integer (pixels)*/
					},
					advanced:{
						updateOnBrowserResize:true, /*update scrollbars on browser resize (for layouts based on percentages): boolean*/
						updateOnContentResize:false, /*auto-update scrollbars on content resize (for dynamic content): boolean*/
						autoExpandHorizontalScroll:false, /*auto expand width for horizontal scrolling: boolean*/
						autoScrollOnFocus:true /*auto-scroll on focused elements: boolean*/
					},
					callbacks:{
						onScrollStart:function(){}, /*user custom callback function on scroll start event*/
						onScroll:function(){}, /*user custom callback function on scroll event*/
						onTotalScroll:function(){}, /*user custom callback function on scroll end reached event*/
						onTotalScrollBack:function(){}, /*user custom callback function on scroll begin reached event*/
						onTotalScrollOffset:0, /*scroll end reached offset: integer (pixels)*/
						whileScrolling:false, /*user custom callback function on scrolling event*/
						whileScrollingInterval:30 /*interval for calling whileScrolling callback: integer (milliseconds)*/
					}
				});
			});
		})(jQuery);		