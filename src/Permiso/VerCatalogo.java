package Permiso;
/*
 * Clase encargada de verCatalogo.
 * @author Alejandro
 */
public class VerCatalogo implements PermisoGeneral {

	@Override
	public void procesa(VentanaPermisosGeneral ventanaPermisos) {
		ventanaPermisos.activarVerCatalogo();
	}


	
}
