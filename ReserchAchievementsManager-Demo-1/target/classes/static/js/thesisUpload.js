
var createNewThesis=function(){
	
}
$(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader('Authorization', 'Bearer '+token)  ;
            });
var uploadFile=function() {
	console.log(new FormData($('#fileList')))
//	var form = document.getElementById("fileupload");
//	form.setAttribute("action", "<%=projectPath%>/services/FileUploadServlet");
//	form.setAttribute("enctype", "multipart/form-data");
//	form.setAttribute("target", "nm_iframe");
//	form.submit();
//	
//	$.ajax({
//		url:'/attachmentUpload',
//		type:'POST',
//		contentType: "multipart/form-data",
//		data:new FormData($('#fileList')),
//		success:function(){
//			
//		},
//		error:function(){
//			
//		}
//	})
//
//	var intervalId = window.setInterval(function () {
//		updateTable(intervalId);
//	}, 2000);
}
$(function () {
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload();

    // Enable iframe cross-domain access via redirect option:
    $('#fileupload').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/cors/result.html?%s'
        )
    );

    if (window.location.hostname === 'localhost:8089') {
        // Demo settings:
        $('#fileupload').fileupload('option', {
            url: '//localhost:8089/jQuery-File-Upload-Java/imgs/',
            maxFileSize: 5000000,
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
            process: [
                {
                    action: 'load',
                    fileTypes: /^image\/(gif|jpeg|png)$/,
                    maxFileSize: 20000000 // 20MB
                },
                {
                    action: 'resize',
                    maxWidth: 1440,
                    maxHeight: 900
                },
                {
                    action: 'save'
                }
            ]
        });
        // Upload server status check for browsers with CORS support:
        if ($.support.cors) {
            $.ajax({
                url: '//localhost:8089/jQuery-File-Upload-Java/imgs/',
                type: 'HEAD'
            }).fail(function () {
                $('<span class="alert alert-error"/>')
                    .text('Upload server currently unavailable - ' +
                            new Date())
                    .appendTo('#fileupload');
            });
        }
    } else {
        // Load existing files:
        $('#fileupload').each(function () {
            var that = this;
            $.getJSON(this.action, function (result) {
                if (result && result.length) {
                    $(that).fileupload('option', 'done')
                        .call(that, null, {result: result});
                }
            });
        });
    }

});

