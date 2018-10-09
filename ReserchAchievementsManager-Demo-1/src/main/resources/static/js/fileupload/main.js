// alert("hello");
var nav= document.getElementsByClassName("left")[0];
var navItem = nav.getElementsByTagName("li");
// alert(navItem.length);
//alert("hello");
var nav= document.getElementsByClassName("left")[0];
var navItem = nav.getElementsByTagName("li");
// alert(navItem.length);

//右边的内容区
var con = document.getElementsByClassName("content-item");

//点击左边的选项，右边内容切换

for(var i=0; i<con.length; i++)
{
	con[i].style.display ="none";
}
con[0].style.display="block";
for(var i=0; i<navItem.length;i++)
{
	navItem[i].index = i;
	navItem[i].onclick=function(){
		
		for(var j=0; j<navItem.length; j++)
			con[j].style.display ="none";
		con[this.index].style.display ="block";

	}
}

//jquery upload file 閮ㄥ垎
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
