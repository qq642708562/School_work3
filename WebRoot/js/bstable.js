function ajaxrequest(){
    var pageSize = $('#pageSize').val();
    var pageNumber = $('#pageNumber').text();
    var data = {
        pageSize:pageSize,
        pageNumber:pageNumber
    };
    $.ajax({
        type: "post",
        url: "queryProduct.do",
        data: data,
        dataType: "json",
        success: function (response) {
            var rows = response.rows;
            var total = response.total;
            pageCount = (Math.ceil(total/pageSize));
            $('#total').text(total);
            $('#pageCount').text(pageCount);
            $('tbody').empty();
            $.each(rows,function(index,row){
                var s = JSON.stringify(row);
                var str = "<tr data='"+s+"'>";
                str = str+'<td><input type="checkbox" value='+row.productCode+'></td>';
                str = str+'<td>'+row.productCode+'</td>';
                str = str+'<td>'+row.barCode+'</td>';
                str = str+'<td>'+row.tradeName+'</td>';
                str = str+'<td>'+row.primaryClassification+'</td>';
                str = str+'<td>'+row.secondaryClassification+'</td>';
                str = str+'<td>'+row.supplyPrice+'</td>';
                str = str+'<td>'+row.minPrice+'</td>';
                str = str+'<td>'+row.recommendedPrice+'</td>';
                str = str+'<td>'+row.commoditySpecifications+'</td>';
                str = str+'<td>'+row.commodityOrigin+'</td>';
                str = str+'<td>'+row.unitOfMeasurement+'</td>';
                str = str+'<td>'+row.commodityProvider+'</td>';
                str = str+'<td>'+row.auditStatus+'</td>';
                str = str+'</tr>';
                $("tbody").append(str);
            });
        },
        error: function () {
            alert("error");
        }
    });
}