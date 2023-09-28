
// Want to use async/await? Add the `async` keyword to your outer function/method.
async function getCatalog() {
    try {
        const response = await axios.get('/catalog', {
            params: {
                category: $('#category option:selected').val()
            }
        });
        console.log("response");
    } catch (error) {
        console.error(error);
    }
}


$(document).ready(function () {
    $("#category").change(function () {
        getCatalog();
    })
});