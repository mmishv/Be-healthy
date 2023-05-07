function addRow(e, parent, container, child, id) {
    if (e.classList.contains('disabled')) {
        e.parentElement.remove();
    } else {
        let temp = e.parentElement.cloneNode(true);
        e.classList.add('disabled');
        e.style.backgroundColor = '#114630a8';
        e.innerHTML = '-';
        temp.children[1].value = '';
        document.getElementById(parent + id).appendChild(temp);
    }
    let ingredients = document.getElementById(parent + id).children;
    let quantity = "quantity";
    let measure = "measure";
    if (parent == 'cur-products') {
        quantity = "cur-quantity";
        measure = "cur-measure";
    }
    for (let i = 0; i < ingredients.length; i++) {
        var c = i + 1;
        ingredients[i].id = container + c;
        ingredients[i].name = container + c;
        let ch = ingredients[i].children;
        ch[0].id = child + c;
        ch[1].id = quantity + c;
        ch[2].id = measure + c;
        ch[0].name = child + c;
        ch[1].name = quantity + c;
        ch[2].name = measure + c;
        if (parent == 'cur-products') {
            ch[3].id = 'cur-button' + c;
            ch[3].name = 'cur-button' + c;
            for (let j = 0; j < 4; j++) {
                ch[j].id += '-m';
                ch[j].name += '-m';
            }
            ingredients[i].id += '-m';
            ingredients[i].name += '-m';
        }
    }
}