/*
 * SimpleModal OSX Style Modal Dialog
 * http://simplemodal.com
 *
 * Copyright (c) 2013 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 */
jQuery(function ($) {
	var OSX = {
		container: null,
		init: function () {
			$("input.osx, a.osx").click(function (e) {
				e.preventDefault();	

				$("#osx-modal-content").modal({
					overlayId: 'osx-overlay',
					containerId: 'osx-container',
					closeHTML: null,
					minHeight: 80,
					maxHeight: 480,
					opacity: 65, 
					position: ['0',],
					overlayClose: true,
					onOpen: OSX.open,
					onClose: OSX.close
				});
			});
		},
		open: function (d) {
			var self = this;
			self.container = d.container[0];
			d.overlay.fadeIn('slow', function () {
				$("#osx-modal-content", self.container).show();
				var title = $("#osx-modal-title", self.container);
				title.show();
				d.container.slideDown('slow', function () {
					setTimeout(function () {
						var h = $("#osx-modal-data", self.container).height()
							+ title.height()
							+ 20; // padding
						d.container.animate(
							{height: h}, 
							200,
							function () {
								$("div.close", self.container).show();
								$("#osx-modal-data", self.container).show();
							}
						);
					}, 300);
				});
			})
		},
		close: function (d) {
			var self = this; // this = SimpleModal object
			d.container.animate(
				{top:"-" + (d.container.height() + 20)},
				500,
				function () {
					self.close(); // or $.modal.close();
				}
			);
		}
	};

	OSX.init();	
	
	var OSX1 = {
		container: null,
		init: function () {
			$("input.osx1, a.osx1").click(function (e) {
				e.preventDefault();				
				$("#osx-modal-content-password").modal({
					overlayId: 'osx-overlay-password',
					containerId: 'osx-container-password',
					closeHTML: null,
					minHeight: 80,
					maxHeight: 480,
					opacity: 65, 
					position: ['0',],
					overlayClose: true,
					onOpen: OSX1.open,
					onClose: OSX1.close
				});
			});
		},
		open: function (d) {
			var self = this;
			self.container = d.container[0];
			d.overlay.fadeIn('slow', function () {
				$("#osx-modal-content-password", self.container).show();
				var title = $("#osx-modal-title-password", self.container);
				title.show();
				
				d.container.slideDown('slow', function () {
					setTimeout(function () {
						var h = $("#osx-modal-data-password", self.container).height()
							+ title.height()
							+ 20; // padding
						d.container.animate(
							{height: h}, 
							200,
							function () {
								$("div.close-password", self.container).show();
								$("#osx-modal-data-password", self.container).show();
							}
						);
					}, 300);
				});
				
			})
		},
		close: function (d) {
			var self = this; // this = SimpleModal object
			d.container.animate(
				{top:"-" + (d.container.height() + 20)},
				500,
				function () {
					self.close(); // or $.modal.close();
				}
			);
		}
	};

	OSX1.init();
	
	
	
	/*  E-Mail */
	
	var OSX2 = {
			container: null,
			init: function () {
				$("input.osx2, a.osx2").click(function (e) {
					e.preventDefault();				
					$("#osx-modal-content-email").modal({
						overlayId: 'osx-overlay-email',
						containerId: 'osx-container-email',
						closeHTML: null,
						minHeight: 80,
						maxHeight: 480,
						opacity: 65, 
						position: ['0',],
						overlayClose: true,
						onOpen: OSX2.open,
						onClose: OSX2.close
					});
				});
			},
			open: function (d) {
				var self = this;
				self.container = d.container[0];
				d.overlay.fadeIn('slow', function () {
					$("#osx-modal-content-email", self.container).show();
					var title = $("#osx-modal-title-email", self.container);
					title.show();
					
					d.container.slideDown('slow', function () {
						setTimeout(function () {
							var h = $("#osx-modal-data-email", self.container).height()
								+ title.height()
								+ 20; // padding
							d.container.animate(
								{height: h}, 
								200,
								function () {
									$("div.close-email", self.container).show();
									$("#osx-modal-data-email", self.container).show();
								}
							);
						}, 300);
					});
					
				})
			},
			close: function (d) {
				var self = this; // this = SimpleModal object
				d.container.animate(
					{top:"-" + (d.container.height() + 20)},
					500,
					function () {
						self.close(); // or $.modal.close();
					}
				);
			}
		};

		OSX2.init();
	
	

		/*  Notification*/
		
		var OSX3 = {
				container: null,
				init: function () {
					$("input.osx3, a.osx3").click(function (e) {
						e.preventDefault();				
						$("#osx-modal-content-notification").modal({
							overlayId: 'osx-overlay-notification',
							containerId: 'osx-container-notification',
							closeHTML: null,
							minHeight: 80,
							maxHeight: 480,
							opacity: 65, 
							position: ['0',],
							overlayClose: true,
							onOpen: OSX3.open,
							onClose: OSX3.close
						});
					});
				},
				open: function (d) {
					var self = this;
					self.container = d.container[0];
					d.overlay.fadeIn('slow', function () {
						$("#osx-modal-content-notification", self.container).show();
						var title = $("#osx-modal-title-notification", self.container);
						title.show();
						
						d.container.slideDown('slow', function () {
							setTimeout(function () {
								var h = $("#osx-modal-data-notification", self.container).height()
									+ title.height()
									+ 20; // padding
								d.container.animate(
									{height: h}, 
									200,
									function () {
										$("div.close-notification", self.container).show();
										$("#osx-modal-data-notification", self.container).show();
									}
								);
							}, 300);
						});
						
					})
				},
				close: function (d) {
					var self = this; // this = SimpleModal object
					d.container.animate(
						{top:"-" + (d.container.height() + 20)},
						500,
						function () {
							self.close(); // or $.modal.close();
						}
					);
				}
			};

			OSX3.init();
});