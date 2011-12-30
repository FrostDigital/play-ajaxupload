var AU = {};	// namespace for ajax-upload

AU.basicUploader = (function($) {
	pub = {};
	
	pub.init = function(opts) {
		new qq.FileUploaderBasic({
			button: $(opts.element)[0],
			action: opts.url,			
			onComplete: opts.onComplete
		});
	}
	
	return pub;
})(jQuery);
