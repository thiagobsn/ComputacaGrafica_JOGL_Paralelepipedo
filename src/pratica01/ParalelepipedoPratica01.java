package pratica01;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;  // GL constants
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_LINES;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.GL2GL3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Ponto;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

@SuppressWarnings("serial")
public class ParalelepipedoPratica01 extends GLCanvas implements GLEventListener {
	
   private static String TITLE = "Pratica 01 - CG - NP2";  
   private static final int CANVAS_WIDTH = 500;
   private static final int CANVAS_HEIGHT = 500;
   
   private GLU glu;
   
   static Ponto p1 = new Ponto(0,0,2);
   static Ponto p2 = new Ponto(2,2,0);
 
   public static void main(String[] args) {

	   SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            GLCanvas canvas = new ParalelepipedoPratica01();
            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
            
            p1 = new Ponto(0,0,2);
            p2 = new Ponto(4,2,0);
 
            final JFrame frame = new JFrame();
            frame.getContentPane().add(canvas);
            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
            
            final Animator animator = new Animator(canvas);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new Thread(new Runnable() {
                        public void run() {
                            animator.stop();
                            System.exit(0);
                        }
                    }).start();
                }
            });
            animator.start();
         }
      });
   }
 
   public ParalelepipedoPratica01() {
      this.addGLEventListener(this);
   }
 
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glClearDepth(1.0f);      // set clear depth value to farthest
      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting    GL_SMOOTH or GL_FLAT
   }
   
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();
 
      if (height == 0) height = 1;
      float aspect = (float)width / height;
 
      gl.glViewport(0, 0, width, height);
 
      gl.glMatrixMode(GL_PROJECTION);
      gl.glLoadIdentity();         
      
      glu.gluPerspective(60.0, aspect, 1.0, 20.0); // fovy, aspect, zNear, zFar
      
      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }
 
   @Override
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  

      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
      gl.glLoadIdentity();
      
      gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
      gl.glLineWidth(1.0f);
      gl.glEnable(GL2.GL_DEPTH_TEST);
      
      glu.gluLookAt(
              6, -2, 8,
              2, 1, 1,	
              0, 1, 0);
      
      drawQuadri2Pontos(gl,p1,p2);
      desenharLinhas(gl);
   }
   
   public void drawQuadri2Pontos(GL2 gl , Ponto p1 , Ponto p2){
	   
	   gl.glBegin(GL_QUADS);
	   
	   gl.glColor3f(255.0f, 255.0f, 0.0f); // Amarelo
       gl.glVertex3f(p1.x, p1.y, p2.z);
       gl.glVertex3f(p2.x, p1.y, p2.z);
       gl.glVertex3f(p2.x, p2.y, p2.z);
       gl.glVertex3f(p1.x, p2.y, p2.z);
       
       gl.glColor3f(0.0f, 255.0f, 255.0f); // Ciano
       gl.glVertex3f(p1.x, p1.y, p1.z);
       gl.glVertex3f(p1.x, p1.y, p2.z);
       gl.glVertex3f(p1.x, p2.y, p2.z);
       gl.glVertex3f(p1.x, p2.y, p1.z);
   
       gl.glColor3f(255.0f, 0.0f, 255.0f); // Magenta
       gl.glVertex3f(p2.x, p1.y, p1.z);
       gl.glVertex3f(p2.x, p1.y, p2.z);
       gl.glVertex3f(p2.x, p2.y, p2.z);
       gl.glVertex3f(p2.x, p2.y, p1.z);
       
       gl.glColor3f(0.0f, 0.0f, 1.0f); // azul
       gl.glVertex3f(p1.x, p1.y, p1.z);
       gl.glVertex3f(p2.x, p1.y, p1.z);
       gl.glVertex3f(p2.x, p1.y, p2.z);
       gl.glVertex3f(p1.x, p1.y, p2.z);

       gl.glColor3f(0.0f, 1.0f, 0.0f); // Verde
       gl.glVertex3f(p1.x, p2.y, p1.z);
       gl.glVertex3f(p2.x, p2.y, p1.z);
       gl.glVertex3f(p2.x, p2.y, p2.z);
       gl.glVertex3f(p1.x, p2.y, p2.z);
   
       gl.glColor3f(1.0f, 0.0f, 0.0f); // Vermelho,
       gl.glVertex3f(p1.x, p1.y, p1.z);
       gl.glVertex3f(p2.x, p1.y, p1.z);
       gl.glVertex3f(p2.x, p2.y, p1.z);
       gl.glVertex3f(p1.x, p2.y, p1.z);

	   gl.glEnd(); 
	   
   }
      
   public void desenharLinhas(GL2 gl){
	   gl.glBegin(GL_LINES);
	   //X
	   gl.glColor3f(1.0f, 0.0f, 0.0f); // R
       gl.glVertex3f(0, 0, 0);
       gl.glVertex3f(6, 0, 0);
       //Y
	   gl.glColor3f(0.0f, 1.0f, 0.0f); // G
       gl.glVertex3f(0, 0, 0);
       gl.glVertex3f(0, 6, 0);
       //Z
	   gl.glColor3f(0.0f, 0.0f, 1.0f); // B
       gl.glVertex3f(0, 0, 0);
       gl.glVertex3f(0, 0, 6);
	   gl.glEnd();
   }
  
   @Override
   public void dispose(GLAutoDrawable drawable) { }
}