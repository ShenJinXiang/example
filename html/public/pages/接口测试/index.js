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
            let _html = ' <tr>\n' +
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
}
