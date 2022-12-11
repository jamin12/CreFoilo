let navigation = document.querySelector('.navigation');
let background = document.querySelector('.background');
let toggle = document.querySelector('.toggle');

console.log(toggle)

$('.toggle').on('click', function(){
    navigation.classList.toggle('active')
    background.classList.toggle('active');
    console.log("i")


})

toggle.onClick = function(){
}
