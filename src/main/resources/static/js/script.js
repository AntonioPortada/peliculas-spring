function actorSelected(select) {
	
	let index = select.selectedIndex;
	let option = select.options[index];
	let id = option.value;
	let nombre = option.text;
	let urlImage = option.dataset.url;
	
	option.disabled = "disabled";
	select.selectedIndex = 0;
	
	agregarActor(id, nombre, urlImage);
	
	let ids = $('#ids').val();
	
	if(ids == "") {
		$('#ids').val(id);
	}
	else {
		$('#ids').val(ids + "," +id);
	}
}

function agregarActor(id, nombre, urlImage) {
	
	let htmlString = `
		<div class="card col-md-3 m-2" style="width: 10rem" >
			<img src="{URL-IMAGE}" class="card-img-top" >
			<div class="card-body" >
				<p class="card-text" >{NOMBRE}</p>
				<button class="btn btn-danger" data-id="{ID}" onclick="eliminarActor(this); return false;" >Eliminar</button>
			</div>
		</div>`;
		
	htmlString = htmlString.replace("{URL-IMAGE}", urlImage);
	htmlString = htmlString.replace("{NOMBRE}", nombre);
	htmlString = htmlString.replace("{ID}", id);
	
	$('#protagonistas-container').append(htmlString);
}

function eliminarActor(btn) {
	
	let id = btn.dataset.id;
	let nodo = btn.parentElement.parentElement;
	let arrayIds = $('#ids').val().split(",").filter(idActor => idActor != id);
	
	$('#ids').val(arrayIds.join(","));
	
	$('#protagonistas option[value="'+ id +'"]').prop("disabled", false);
	
	$(nodo).remove();
}

function previsualizar() {
	
	let reader = new FileReader();
	
	reader.readAsDataURL(document.getElementById("archivo").files[0]);
	
	reader.onload = function (e) {
		let vista = document.getElementById("vista_previa");
		
		vista.classList.remove("d-none");
		vista.style.backgroundImage = 'url("'+ e.target.result +'")';
	}
}
