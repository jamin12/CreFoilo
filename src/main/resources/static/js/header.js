let navigation = document.querySelector('.navigation');
let background = document.querySelector('.background');
let toggle = document.querySelector('.toggle');

$('.toggle').on('click', function(){
    navigation.classList.toggle('active')
    background.classList.toggle('active');


})

toggle.onClick = function(){
}
