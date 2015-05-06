package event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Ponto;


public class KeyEventCanvas implements KeyListener {
	
	public float DISTANCIA = 0;
	
	public final Integer CONSTANTE = 5;
	
	public float eyeX = 0;
	public float eyeY = 0;
	public float eyeZ = 0;
	
	public float centroX = 0;
	public float centroY = 0;
	public float centroZ = 0;
	
	public float centroXInicial = 0;
	public float centroYInicial = 0;
	public float centroZInicial = 0;
	
	public Integer upX = 0;
	public Integer upY = 1;
	public Integer upZ = 0;
	
	public boolean isLines = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
//		 System.out.println(e.getKeyCode());
		 switch (e.getKeyCode()) {
		 
			case 37: //LEFT
				eyeX = eyeX - CONSTANTE;
				centroX = centroX - CONSTANTE;
				showLookAt();
				break;
				
			case 38: //UP
				eyeY = eyeY + CONSTANTE;
				centroY = centroY + CONSTANTE;
				showLookAt();
				break;
				
			case 39: //RIGHT
				eyeX = eyeX + CONSTANTE;
				centroX = centroX + CONSTANTE;
				showLookAt();
				break;
				
			case 40: //DOWN
				eyeY = eyeY - CONSTANTE;
				centroY = centroY - CONSTANTE;
				showLookAt();
				break;
				
			case 91: //[
				eyeZ = eyeZ + CONSTANTE;
				centroZ = centroZ + CONSTANTE;
				showLookAt();
				break;
				
			case 93: //]
				eyeZ = eyeZ - CONSTANTE;
				centroZ = centroZ - CONSTANTE;
				showLookAt();
				break;
				
			case 222: //'
				if(isLines){
					isLines = false;
				}else{
					isLines = true;
				}
				break;
				
			case 49: //1
				resetEye();
				eyeX = centroXInicial;
				eyeY = centroYInicial;
				eyeZ = DISTANCIA;
				setUpY();
				showLookAt();
				break;	

			case 50: //2
				resetEye();
				eyeX = centroXInicial;
				eyeY = DISTANCIA;
				eyeZ = centroZInicial;
				setUpZ();
				showLookAt();
				break;
			
			case 51: //3
				resetEye();
				eyeX = centroXInicial;
				eyeY = centroYInicial;
				eyeZ = (-DISTANCIA  + (2 * centroZInicial));
				setUpY();
				showLookAt();
				break;
				
			case 52: //4
				resetEye();
				eyeX = centroXInicial;
				eyeY = (-DISTANCIA  + (2 * centroYInicial));
				eyeZ = centroZInicial;
				setUpZ();
				showLookAt();
				break;
				
			case 53: //5
				resetEye();
				eyeX = DISTANCIA;
				eyeY = centroYInicial;
				eyeZ = centroZInicial;
				setUpY();
				showLookAt();
				break;
				
			case 54: //6
				resetEye();
				eyeX = (-DISTANCIA  + (2 * centroXInicial));;
				eyeY = centroYInicial;
				eyeZ = centroZInicial;
				setUpY();
				showLookAt();
				break;
		default:
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void setUpX(){
		upX = 1;
		upY = 0;
		upZ = 0;
	}
	
	public void setUpY(){
		upX = 0;
		upY = 1;
		upZ = 0;
	}
	
	public void setUpZ(){
		upX = 0;
		upY = 0;
		upZ = 1;
	}
	
	public void setCentroAndEye(Ponto p1, Ponto p2){
		//Centro
		centroXInicial = (p1.x + p2.x) /  2;
	    centroYInicial = (p1.y + p2.y) /  2;
	    centroZInicial = (p1.z + p2.z) /  2;
	    //Centro da Imagem
	    centroX = centroXInicial;
	    centroY = centroYInicial;
	    centroZ = centroZInicial;
	    DISTANCIA = 10 * centroZInicial;
	    //EYE
	    eyeX = centroXInicial;
	    eyeY = centroYInicial;
	    eyeZ = DISTANCIA;
	}
	
	public void resetEye(){
		eyeX = centroXInicial;
	    eyeY = centroYInicial;
	    eyeZ = DISTANCIA;
	    centroX = centroXInicial;
	    centroY = centroYInicial;
	    centroZ = centroZInicial;
	}
	
	public void showLookAt(){
		System.out.println("[Eye]    X: "+eyeX+" Y:"+eyeY+" Z:"+eyeZ);
		System.out.println("[Centro] X: "+centroX+" Y:"+centroY+" Z:"+centroZ);
		System.out.println("[UP]     X: "+upX+" Y:"+upY+" Z:"+upZ);
		System.out.println();
	}

}