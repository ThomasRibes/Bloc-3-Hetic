
async function getCatalog() {
    const response = await axios.get('/catalog/filter', {
        params: {categoryId: $('#category option:selected').val()}})
            .then(function (html) {
//                add check if data is empty
//                add product title do id productTitle
                document.getElementById("ourFilteredProducts").innerHTML = html.data;
            })
            .catch(function (error) {
                console.log(error);
            })
}

$(document).ready(function () {
    $("#category").change(function () {
        getCatalog();
    })
});