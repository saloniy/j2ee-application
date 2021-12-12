$(document).ready(function() {
	var today = new Date();
	var month = today.getMonth() + 1;
    var day = today.getDate();
    var year = today.getFullYear();

    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    var maxDate = year + '-' + month + '-' + day;    
	$('#purchaseDate').attr('max', maxDate); // disable future dates
})

/**
 * call to update the status of a claim when changed by the admin
 */
function claimStatusChanged(id) {
   	    var statusSelectBoxValue = $('#claimStatus' + id).val();
   	    var updatedMsg = $('#updatedMsg' + id);
   	    var errorMsg = $('#errorMsg' + id);
   	    $.post('update-claim-status', {claimId: id, status: statusSelectBoxValue}).done(function(data) {
   	        if(data == "Done") {
   	        	if(statusSelectBoxValue == "Approved"){
   	        		$('#claimStatus' + id).attr("disabled", true);
   	        	}
   	            updatedMsg.addClass('show').removeClass('hide');
   	            setTimeout(function(){
   	                updatedMsg.addClass('hide').removeClass('show');
   	            }, 5000);
   	        } else {
   	        	errorMsg.addClass('show').removeClass('hide');
 	   	        setTimeout(function(){
 	   	        	errorMsg.addClass('hide').removeClass('show');
 	   	        }, 5000);
   	        }
   	    })
   	}

/**
* call to fetch all users as per the value in the search box
 */

function searchUser() {
	var searchTerm = "%" + $('input[name=searchUser]').val() + "%";
	var userTable = $('#allUsersData');
	$.post('search-user', {search: searchTerm}).done(function(data) {
		userTable && userTable.html(data);
	})
}

/**
 * call to register device 
 */
function registerDevice() {
	var username = $('#username').val();
	var productId = $('#product').val();
	var serialNum = $('#serialNum').val();
	var purchaseDate = $('#purchaseDate').val();
	var successMsg = $('#successMsg');
	var errorMsg = $('#errorMsg');
	$.post('register-device', {username: username, productId: productId, serialNum: serialNum, purchaseDate: purchaseDate}).done(function(data) {
		if(data.indexOf("productError") > -1) {
			$('#productError').addClass('show').removeClass('hide');
		} else {
			$('#productError').addClass('hide').removeClass('show');
		}
		if(data.indexOf("serialNumError") > -1) {
			$('#serialNumError').addClass('show').removeClass('hide');
		} else {
			$('#serialNumError').addClass('hide').removeClass('show');
		}
		if(data.indexOf("purchaseDateError") > -1) {
			$('#purchaseError').addClass('show').removeClass('hide');
		} else {
			$('#purchaseError').addClass('hide').removeClass('show');
		}
        if(data == "Done") {
            successMsg.addClass('show').removeClass('hide');
            setTimeout(function(){
                successMsg.addClass('hide').removeClass('show');
            }, 5000);
        } else if(data == "Error"){
        	errorMsg.addClass('show').removeClass('hide');
   	        setTimeout(function(){
   	        	errorMsg.addClass('hide').removeClass('show');
   	        }, 5000);
        }
    })
}