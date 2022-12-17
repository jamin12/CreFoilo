const deleteProject = (e) => {
  const header = $("meta[name='_csrf_header']").attr('content');
  const token = $("meta[name='_csrf']").attr('content');

  const portfolioId = document.querySelector("#portfolioId").value;
  $.ajax({
    url: `/setting/project/${e.value}`,
    method: "DELETE",
    async: false,
    beforeSend: function (xhr) {
      xhr.setRequestHeader(header, token);
    },
    success: function (data) {
      location.href = `/setting/project/${portfolioId}`
    },
    error: function (error) {
    },
  })
}