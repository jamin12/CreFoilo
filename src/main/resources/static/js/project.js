const deleteProject = (e) => {
  const portfolioId = document.querySelector("#portfolioId").value;
  $.ajax({
    url: `/setting/project/${e.value}`,
    method: "DELETE",
    async: false,
    success: function (data) {
      location.href = `/setting/project/${portfolioId}`
    },
    error: function (error) {
    },
  })
}