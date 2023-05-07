// function fillModal(id, ingredients) {
//     let modal_id = 'editMeal' + id;
//     let temp = $("#editMeal").clone().attr('id', modal_id).insertAfter("#editMeal");
//     temp.find('*[id]').each(function () {
//         $elm = $(this);
//         $elm.attr('id', $elm.attr('id') + id);
//     });
//     temp.find('*[name]').each(function () {
//         $elm = $(this);
//         $elm.attr('name', $elm.attr('name') + id);
//     });
//     $('#mealTitle' + id).attr('value', $('#meal-name' + id).text());
//     for (let i = 1; i <= document.getElementById('meal-scroll' + id).children.length + 1; i++) {
//         let newRow = document.createElement('div');
//         newRow.className = "row cur-prod-option";
//         newRow.id = "cur-prod-option" + i + "-m" + id;
//         newRow.name = "cur-prod-option" + i + "-m" + id;
//         document.getElementById('cur-products' + id).append(newRow);
//         let newProduct = document.createElement('select');
//         newProduct.className = 'form-control col-sm-6';
//         newProduct.id = "cur-product" + i + "-m" + id;
//         newProduct.name = "cur-product" + i + "-m" + id;
//         let newQuantity = document.createElement('input');
//         newQuantity.className = 'form-control col-sm-2';
//         newQuantity.type = 'number';
//         newQuantity.id = "cur-quantity" + i + "-m" + id;
//         newQuantity.name = "cur-quantity" + i + "-m" + id;
//         let newMeasure = document.createElement('select');
//         newMeasure.className = 'form-control col-sm-2';
//         newMeasure.id = "cur-measure" + i + "-m" + id;
//         newMeasure.name = "cur-measure" + i + "-m" + id;
//         let newMOption = document.createElement('option');
//         newMOption.selected = true;
//         newMOption.textContent = 'г';
//         newMeasure.append(newMOption);
//         let newButton = document.createElement('button');
//         newButton.id = "cur-btn" + i + "-m" + id;
//         newButton.addEventListener('click', function () {
//             addRow(this, 'cur-products', 'cur-prod-option', 'cur-product', 1);
//         });
//         if (i != document.getElementById('meal-scroll' + id).children.length + 1) {
//             newButton.className = 'col-sm-1 ing-button disabled';
//             newButton.style.backgroundColor = '#114630a8';
//             newButton.innerHTML = '-';
//             newQuantity.value = document.getElementById('prod-q' + i + '-m' + id).textContent;
//         } else {
//             newButton.className = 'col-sm-1 ing-button';
//             newButton.innerHTML = '+';
//             newQuantity.placeholder = 'кол-во';
//             newQuantity.required = true;
//         }
//         newRow.append(newProduct);
//         newRow.append(newQuantity);
//         newRow.append(newMeasure);
//         newRow.append(newButton);
//
//         $("#editMeal" + id).on("hidden.bs.modal", function () {
//             $("#editMeal" + id).remove();
//         });
//     }
//     $('#' + modal_id).modal('show');
// }
//
// function deleteModal(id) {
//     document.getElementById("editMeal" + id).remove();
// }
