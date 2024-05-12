
	public class APP {
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			conexion con = new conexion();
			Script js = new Script();
			
			con.conexionyHtml();
			js.escribirJs();

		}
}