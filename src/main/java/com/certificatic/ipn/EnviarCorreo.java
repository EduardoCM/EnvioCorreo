package com.certificatic.ipn;

public class EnviarCorreo {

	public static void main(String[] args) {

		Correo correo = new Correo("ecm3198@gmail.com", 
				"Registro en nuestro portal segunda prueba", 
				"Bienvenido a nuestro portal de clientes 2");


		System.out.println(":::: Inicio :::::");
		if (correo.enviarCorreo()) {
			System.out.println("Se envio de manera exitosa");
		} else {
			System.out.println("Error al enviar correo");
		}
		System.out.println(":::: Fin ::::");

	}

}
