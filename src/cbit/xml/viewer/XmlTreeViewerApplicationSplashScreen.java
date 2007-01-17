package cbit.xml.viewer;

/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.awt.*;
import javax.swing.*;
/**
 * This type was generated by a SmartGuide.
 */
public class XmlTreeViewerApplicationSplashScreen extends JWindow {

class IvjEventHandler implements java.awt.event.WindowListener {
		public void windowActivated(java.awt.event.WindowEvent e) {};
		public void windowClosed(java.awt.event.WindowEvent e) {};
		public void windowClosing(java.awt.event.WindowEvent e) {
			if (e.getSource() == XmlTreeViewerApplicationSplashScreen.this) 
				connEtoC1(e);
		};
		public void windowDeactivated(java.awt.event.WindowEvent e) {};
		public void windowDeiconified(java.awt.event.WindowEvent e) {};
		public void windowIconified(java.awt.event.WindowEvent e) {};
		public void windowOpened(java.awt.event.WindowEvent e) {};
	};
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private JLabel ivjJLabel1 = null;
	private JPanel ivjJWindowContentPane = null;
/**
 * XmlTreeViewerApplicationSplashScreen constructor comment.
 */
public XmlTreeViewerApplicationSplashScreen() {
	super();
	initialize();
}
/**
 * XmlTreeViewerApplicationSplashScreen constructor comment.
 * @param owner java.awt.Frame
 */
public XmlTreeViewerApplicationSplashScreen(Frame owner) {
	super(owner);
}
/**
 * XmlTreeViewerApplicationSplashScreen constructor comment.
 * @param owner java.awt.Window
 */
public XmlTreeViewerApplicationSplashScreen(Window owner) {
	super(owner);
}
/**
 * connEtoC1:  (XmlTreeViewerApplicationSplashScreen.window.windowClosing(java.awt.event.WindowEvent) --> XmlTreeViewerApplicationSplashScreen.dispose()V)
 * @param arg1 java.awt.event.WindowEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.WindowEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.dispose();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Return the JLabel1 property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getJLabel1() {
	if (ivjJLabel1 == null) {
		try {
			ivjJLabel1 = new javax.swing.JLabel();
			ivjJLabel1.setName("JLabel1");
			ivjJLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cow.gif")));
			ivjJLabel1.setText("");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJLabel1;
}
/**
 * Return the JWindowContentPane property value.
 * @return javax.swing.JPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JPanel getJWindowContentPane() {
	if (ivjJWindowContentPane == null) {
		try {
			ivjJWindowContentPane = new javax.swing.JPanel();
			ivjJWindowContentPane.setName("JWindowContentPane");
			ivjJWindowContentPane.setBorder(new javax.swing.border.EtchedBorder());
			ivjJWindowContentPane.setLayout(new java.awt.BorderLayout());
			getJWindowContentPane().add(getJLabel1(), "Center");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJWindowContentPane;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	// exception.printStackTrace(System.out);
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	this.addWindowListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("XmlTreeViewerApplicationSplashScreen");
		setSize(300, 220);
		setContentPane(getJWindowContentPane());
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		XmlTreeViewerApplicationSplashScreen aXmlTreeViewerApplicationSplashScreen;
		aXmlTreeViewerApplicationSplashScreen = new XmlTreeViewerApplicationSplashScreen();
		aXmlTreeViewerApplicationSplashScreen.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		aXmlTreeViewerApplicationSplashScreen.show();
		java.awt.Insets insets = aXmlTreeViewerApplicationSplashScreen.getInsets();
		aXmlTreeViewerApplicationSplashScreen.setSize(aXmlTreeViewerApplicationSplashScreen.getWidth() + insets.left + insets.right, aXmlTreeViewerApplicationSplashScreen.getHeight() + insets.top + insets.bottom);
		aXmlTreeViewerApplicationSplashScreen.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of javax.swing.JWindow");
		exception.printStackTrace(System.out);
	}
}
/**
 * 
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private static void getBuilderData() {
/*V1.1
**start of data**
	D0CB838494G88G88GE0FBB0B6GGGGGGGGGGGG8CGGGE2F5E9ECE4E5F2A0E4E1F4E1359A8BF0D45555E9D153B63654C9DBF4F01AD2F422C60BE3342899A7355A414A0CB18396C71CDA0DB54E20250AAD250C3D59FCC884C31020D8292490F483E1A1FCB4AC8B668B84B6901285520AADAF3BB749A6EF77ED5E3E6CE689C84F391F5D17301B9026E6664CFD775CF74FBD7FF36E5B100A0D29C579168AC2720B08787BAC1F101AAD0464857E16A7F1F51F641492535FBF8156103B54C2980F40DA
	72311C52F2F203D2827333B0CFB91AD37ABEBC2FA5B7AAB51B61A141B905307670E7395F9B7F1C9BBD721C95EC7ED767AA984BG4E818E4774CF707C35ED6B85FE0A40CB48379012E245F7ECED9B84EEB13CD38238F6G0F153FFF81C64E403576BA715E596F2711A53F56AD6FA09D93CD898B45DED24D47CA32ACBF2D9A4FBA5336CA6C2903F9GG05AFA099EDF6989D6D833D875D5DDE43B66CD3EDA1AF8DD35DEE6F7229AE1D5253EC5A908828DE37E2F8B5FFE7C0D502FD1DEED87393D23595697F382D0451
	A3E491501E45F04FC4704CBEF83E86E05AD609CFBD0F78A5703CEADBCEE955B6A96701CF9CC20E2E111F13F93BFC052B93486958466573EC0B4939F060B94B874CA6B994F51F8AEB0F82ACGD88E3021A627548170A17305E7BCDBE1EC68B61AB41FCF7303F40D2AC7EF53BCD405E73B9DD6842E033AB55DC388377717EDB5F178998F783E01C96F63BAC95BADED1F5EF1B7D9F86F8FADAB6268E4E15A0845D543F5215644F4424F1E19706C641A513EB97A6CB344FC76704F66979445B9BBE57A7C02D4C1A743F4
	F6EE8D5A3A0D59FA9EBC678B7F6078B38CEF5D9E43F3BF7DAD73991C57415A746D52B73A4AA4AF4E4299243C76C7F175B06D11E48B31F19E565B4672D2B102341BEDE3F9790861DD36313C5CF6DE72620135B9G5C3F1E6D45382D5911D3FA84609C40B040B4008C00B9BB240FED22BB2E50475A955DE89C722A9E2A933E77D97A9E0CF4585015D6FF50D07CEEFADA513DCA37CA03C43C13CBD1875D23763D42FC5F8DE79C26FE2A33F8856A0A0F9AD48F72F7DE27EB709D1D32D51757C761GDF00C83A4B68DA74
	A3A5E81C8AF89403465E606B169E34C9C8B98D9401GBCF33D14F8D1DFF9A0BF59897A5BA975F03187699D233A1F2AF63BD353860682AE14041C437748C3ACF7D44133E32754F1E1BF62E642BCB3CAA72367DD931DE62F9B59D774080C314E73181B33F7CA1FF92DCB7ACC73BBF7111A79BF086BB3A5374CAFA03542E7EA47464E170CA6724567AFC53AA69947484F64F245DB13BE23E0926FF27D6A83E82BFA1077AC4005DA2963BF74D4F21FEDD402D4FA69899D9C0CFAE29A67FC7EFD24D068134F2B58BC25D6
	6AAA7DFCD79CFD7F3F001F7198BBE3B272CF8638CEDE6A45BAC4C354EFB453G75FB60A1A8569675222F09B86D5122FBDEE778G44F59384D2B00ADF5A0B312798066E6D9EB2E8A7B58C2F3FB736CE7A702C60D0F7A0169264D9AB7AB07FEAE1086B687B2B7AB0A65C1A5FCF19F122DB44FA990BC92AD29FB05D248D798D0E2F70222D86E8C46404755EE202B12D8EE1FE613D41ACE5A54A20FBFDE0F2CCD124FC9746EBCE3437C14884FBE30F138B6352DA99FB614DB2762A4E5FCE327E9B30442B9D53EE7D1E25
	EB1728DB3BE23147F9AA6D0F4F7BEA169B84BE48F97D5609AA982F24E3617EFF270A3413614CD400593B247FFF279F57E50E910E5F2429AAD7EFD46F438365E8A7BAB8C4A1B5E3B2BB9655652501F7472C1D96FA656B578F20DED5D39EF46CC63D1E9A117DE076AE1E8B73F6F15DD66D663A6132BE3D92F9E4BDDB3F92D206ED41B078105DFE740C576F5142C476E40E6332A7CB5AADFB2FD9A7D06EAC18AFGD83CDB5A6B29F7E46F15B210C6DC336695A46AB1CB76F0DEEA76446CE5CD8E97F03D3E6943B3D5D8
	3B30C76A54221A73FFC3B028393D4CACC29F7728E6FBF25C7D6A5BB076F87520518A81B7ACF51729A23FD81B84DA546A2FC6E42D1E8A674E81A86031BB32933D11A9464607A6D58B023E2C28BB1B92B6ECAC32EDDCF9AD78DC4D75FC5B7B4803479B8CE8C1EA2DBEAD7B2299EB6B05AE665EBD03B8AE7D47922FCFCDDA5DE7A1FB390E2CFBE33A4A4EF94E128B4073D74371717AC2E36F65350377ACD56DA8EF525E313D0C0D61276D9D5B4B5CFFC26AA78D56D6FDAA6B6C948DE3B9G73E40019G19G8B8116FF
	AA6D576C5BCDA668E3DA7D50DE789535F3D462127D042F00679AB1FF4BD7A4F38D11F9FC4A48C47D717A4FC66B026F3BF142FD0EB17B385CAF84B056DC3096GC8821889B087A0972024CE4A1E64BFB0116C4CE5DCDA93600EC0CCB70D4D45301F77B9DDAC9E2DC03B3ECE56482D6D1807BD51799EB61F921DE71E3812DEG77DFFD8D1DFFC5E7DCED0D65FA3CC1CBDCC3D33478B574E6ADFE8DCD5556F0BC50B0F74EF2BDCD9B3F464E52625558FFB099DFB65611B87574B8B450C287EB49C3A325621D96C3EB2A
	0D52DD94C8AC639B0178B27EA500F9B6288D69EEBAD68EB238BE3A96732AD6037A109DD1A387F13F21683D74B2BD14337DFCAD517E8F8693690A6740FB42D82BFA608281684EGF5936BBE74E56D383987C8837F1B831087D032CF665E10876B2028CA4000416C6587C572BE02361BE98476711A1782E39AC096BB675B47AB467860831058ECB072754CD16BA8CBB5B12F3FB6EA9DED70AF0BF93D7204F93D48341F677AA736483A387C09D9C43DEFCA42EFAFA5F6D19745B8B577267C5CE74F97F0BD3ED10D3246
	2959289015576BDB5ACBC4CCFA6C32DE173AB0EFCF07F98640DC3B2C573FD8A1794AF8708EB27B3AE64B4A84FC658AFE8A770BDA7462CD7931BC38C0C73E7640DABB0081108C108AB0FB3F4C018F8EE20E45CC67719A8D0621387BD8251689905B2D989A1D046715996D665CBC9768BD3FBF1627166CCFD45BAEB219CB9CE3EB5B758C3F5EB136362DF6495AE60535E4072CED9F99E85B45B0DFF5A027F4A7C0B3C05781A957DB031B38DCF1E4EA7174E24C614D5B107174BBBDF1FC6769935F577F28E3BF512D99
	0666C37A927FE79D75E5E88106643865BA5AD9777676997CD5F9778E327E0A76B0B42FAB8FB03FB1C02EA4G0EFB75A46252E11E95457D26D3561F5C83323EFC5305752664201C5F40669B2273F52E495EC54B843DAB2FBF362BBA63CA6B91575B47C6E25BEDB7A6365D27866BD1C1672EC8C0644E7D865E19341E1EA00D7AC83DD19E4535865B2153441C4B73E1F6FBB1333F4E12F46AC134494B519C680039CB68ADC90CFCDFE1876ED31508B6E448FD9FF648FDF9B0A71F0B5896E37D67AC77F52E9FC7AF1C76
	CF943441D12752CD55DA124576BD6CB26744A440E1BF128E2308371372DBF2C9BD74B27572DBF2D16596F2F90E33F1016D7696435DC7BD0DC2FC42731B13519A869AD381526AE5FE9B7ED86637745BE610A369716FA34F3BAEDA326A394C3975319EFB7140F3964EEBD30764353281E23FC1727AFB5B07E6DE719EE037F35E1CDC511C3FEB587E2930AF8DA02BC17257BFA0795BF067CF5919096A4202864E57720698FF3256C0DD68C9D09718C5F8DDF853A56B0223D11EFF7110793E16397AE2427CEFB472F3D3
	9AE36757A57F29C04477492B7B1E3DF9527BF88E4FE2761E5748F3F8FE947FB843BFEF42F37BF5C4FDED912C35B7495C5E91C23DE540BC9BA08F00B46714D682B81AE5FE1FEF605DEF5C5E5D25851C1094D4D39B4965EB6D38BA3D1C1974BEAE7F9D1DA8E7FB73D83D5C4B70E71B476A653A28DF77415A5CE6291774B06AE5E7CBCE2993A0G1084B09DA023C56AE563905E67CEAB7DC2A3D856B51D2984109D1A463AB4EC58942F1F6A2C8C6A70E888991739D046B9AD23F37CC2B1DF5CF2F96E673C3D97413CD2
	F8886C84D087508E90B8A4799A981A701E59B29C771E49797A55C99235431326672E13D7D3DFA6E777412F758C2E1FAFC209EBD8F7A87E7D22AF947F8E658F0DFFC78A0592FF073C98B2FF0764724F6A0C697B8153F3F247572F7BDB3F56B3384C7705936BBEB3BCF17F70D8B8FE6F77C478725EAFBB9C3F77FBBAFCF96F3766946226C28C259E12FDDEF584F199B0FFB40A7BEEC476FE5951F87BA812D826AD11787E34AD925F1FEAA3635F4977C592799B6FC32C27306F7442255AF59879BD4864DA8E3C169C16
	77497A883EE3GD3538E63EF734BAE211FADE66F5F9A299C47068B8F731C4477F5D9C8745C77A278FD47CF431566162555277452D6E0460AD7E79454DAED03478E4AEF6356CDBB5CDA58566B6D59D40D77C5DE13D343665F696B5E7EC65CEF5E88F5C7B8BFCEB1AEDFF7F3BE27716497E69A65B72B167C84B426083DD30F446ABA17691188760BCD943F49C95E42545C2FEC58D8EFD913006E23025E62A3237B951E431FAEE03F5535C25F57AA7D68964BB612381E346BDA006AC604E35917F0C16BA9B427700F97
	ADDB92510034FF94ECF3223B1F3A8D17529B65650DA23479B9606382C0AA40EC0039G39GCB814A0FCA9E2D8556F1F8640DDF2C50095FE08B4A4CBE4BD9977C3ED000354C6B0776DED13D67792F8C70223FD75CAD0B9247D6D1714479222CB8FE3E28A83EBCDFFCD09CBFDFECACB6678B0E2B92F47568C7AD0E5FDA0C7DAB3FE334A9FEF0FF0CDD7EFFB335832F40187EAAA9026BC7D0A71B5D1A4F666D765906C2BE1B52ED58A8A8530654BC51407977AF4F4D6EFBFC5DA68BA9EAA5ABFB3CD475F43250F112F2
	B6EB26C1376E8D205E8C722E9323EF072A78FBF7F0AD6EF712B56CBD99F2C8FE10FCC5ACCE3256C46E10D7257E58D219E969D40753A0E51C321F9ABBE03EFF98FF654EB7C8054DF6B664262ADAB96CD34F0AD88EA01122C0B41E2BAF19E3F169356E04711CFDCC5CC10E45E2067B68CC76FBC825937AD2277445A56A76F1FC3D5327282A2128838DFE8F7AFDE3442031BCFB3B8F698765BBE2D1FC5774A15F7D26BD7F83D0CB87889F5F6894428DGGD4A5GGD0CB818294G94G88G88GE0FBB0B69F5F6894
	428DGGD4A5GG8CGGGGGGGGGGGGGGGGGE2F5E9ECE4E5F2A0E4E1F4E1D0CB8586GGGG81G81GBAGGG7C8DGGGG
**end of data**/
}
}
