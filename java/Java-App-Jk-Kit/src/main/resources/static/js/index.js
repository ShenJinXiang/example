$(() => {
    bindEvent();
});

function bindEvent() {
    $("body").on('keypress', '.param-input', function (e) {
        let $tr = $(this).closest('tr'),
            $tbody = $(this).closest('tbody'),
            $next = $tr.next(),
            $cztd = $tr.find('td.cz-td'),
            $delBtn = $cztd.find("a.del-tr-btn"),
            trSize = $tbody.children('tr').size(),
            $checkbox = $tr.find('input.param-checkbox');
        if ($(this).val() !== '') {
            $checkbox.prop("checked", true);
        }
        if ($delBtn.size() <= 0 && trSize > 1) {
            $cztd.append($('<a class="del-tr-btn">删行</ac>'));
        }
        if ($next.size() <= 0) {
            let _html = ' <tr class="param-tr">\n' +
                '                                <td class="check-td"><input type="checkbox" class="param-checkbox"></td>\n' +
                '                                <td><input type="text" class="form-control param-input param-name-input"></td>\n' +
                '                                <td><input type="text" class="form-control param-input param-value-input"></td>\n' +
                '                                <td class="cz-td">\n' +
                '                                </td>\n' +
                '                            </tr>';
            $tr.after($(_html));
        }
    });
    $("body").on('click', ".del-tr-btn", function (e) {
        let $tr = $(this).closest('tr'),
            $tbody = $(this).closest('tbody'),
            trSize = $tbody.children('tr').size();
        if (trSize > 1) {
            $tr.remove();
        }
    });

    $("#jmBtn").click(function () {
        if ($(this).prop('checked')) {
            $(".jm-data-container").show();
        } else {
            $(".jm-data-container").hide();
        }
    });

    $("#tjBtn").click(() => {
        let url = $("#url").val();
        if ($.trim(url) === '') {
            alert('缺少url');
            return;
        }
        params = {};
        $(".param-tr").each((index, item) => {
            let check = $(item).find('.param-checkbox').prop("checked"),
                name = $.trim($(item).find('.param-name-input').val()),
                value = $.trim($(item).find('.param-value-input').val());
            if (check && name !== '') {
                params[name] = value;
            }
        });
        let check = $("#jmBtn").prop('checked') ? 1 : 0;
        $.ajax({
            url: '/tj',
            data: {
                "url": url,
                "check": check,
                "params": JSON.stringify(params)
            },
            type: 'POST',
            success: function (json) {
                console.log(json);
                if (json.success) {
                    $("#param").val(json.param);
                    $("#paramStr").val(json.paramStr);
                    $("#result").val(json.result);
                    $("#resultStr").val(json.resultStr);
                } else {
                    alert(json.msg);
                }
            },
            error: function (json) {
                alert(json);
            }

        });
    });
}
