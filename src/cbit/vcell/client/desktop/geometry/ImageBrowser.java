package cbit.vcell.client.desktop.geometry;

/**
 * Insert the type's description here.
 * Creation date: (6/10/2004 8:32:57 PM)
 * @author: Anuradha Lakshminarayana
 */
public class ImageBrowser extends javax.swing.JPanel {
	private cbit.vcell.desktop.ImageDbTreePanel ivjImageDbTreePanel1 = null;
/**
 * ImageBrowser constructor comment.
 */
public ImageBrowser() {
	super();
	initialize();
}
/**
 * ImageBrowser constructor comment.
 * @param layout java.awt.LayoutManager
 */
public ImageBrowser(java.awt.LayoutManager layout) {
	super(layout);
}
/**
 * ImageBrowser constructor comment.
 * @param layout java.awt.LayoutManager
 * @param isDoubleBuffered boolean
 */
public ImageBrowser(java.awt.LayoutManager layout, boolean isDoubleBuffered) {
	super(layout, isDoubleBuffered);
}
/**
 * ImageBrowser constructor comment.
 * @param isDoubleBuffered boolean
 */
public ImageBrowser(boolean isDoubleBuffered) {
	super(isDoubleBuffered);
}
/**
 * Return the ImageDbTreePanel1 property value.
 * @return cbit.vcell.desktop.ImageDbTreePanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public cbit.vcell.desktop.ImageDbTreePanel getImageDbTreePanel1() {
	if (ivjImageDbTreePanel1 == null) {
		try {
			ivjImageDbTreePanel1 = new cbit.vcell.desktop.ImageDbTreePanel();
			ivjImageDbTreePanel1.setName("ImageDbTreePanel1");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjImageDbTreePanel1;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	exception.printStackTrace(System.out);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("ImageBrowser");
		setLayout(new java.awt.BorderLayout());
		setSize(362, 501);
		add(getImageDbTreePanel1(), "Center");
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
		javax.swing.JFrame frame = new javax.swing.JFrame();
		ImageBrowser aImageBrowser;
		aImageBrowser = new ImageBrowser();
		frame.setContentPane(aImageBrowser);
		frame.setSize(aImageBrowser.getSize());
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		frame.show();
		java.awt.Insets insets = frame.getInsets();
		frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
		frame.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of javax.swing.JPanel");
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
	D0CB838494G88G88GC5FBB0B6GGGGGGGGGGGG8CGGGE2F5E9ECE4E5F2A0E4E1F4E135D8EBCC9C57956687092A16282421095DD25535138A3BA4212D53382D951148BF2C24BF9C97372EC4D42A10AA6E53D2DCB50D10F2F797E2280D03B12AEC95A5A43C960AF9999B3C009F009393E38719B526155BB06CDE58015919E5E6969607D6BD675E393B836CC26D5416BE5F1DF31F67751DFBA1956FEEA8F2B8AB88F194916BDF27031040F1C27E907E59F6A16B2A5FDDC2EC7F5E839CA585878B21
	3D8CFD8759773E778FC3FB84B08EB2ECB321ADA30E6A6316BC59128BB81C04C45CB826323A52126D07B145A09B820C3BE36379816E73F4D95F379E4CA0871E571D25380EEDCD01ECEBEE3E55D611B79CA98D3857256A43561CCA787682D0F997797948E9E8FB06E7264E79A6E433F94EC79525592748D4B51B7D5418B135D073945502545497BC1E4120B4C57BF4ED5E20BA4CABEE44359F28AEE6EB91328B56CDE33201C65CEF88FE7B810BG2ECBEFFD0FF55631CF1FA6B96D41423793683237116BC07E9653A5
	A978127384333B016EA2BD50B78EB0816BG5BGFB81FF053EE3243F38915A0B93E63F968CEA6A45D028CF716BD7B5BFD560374783BD166CBA75E93A1F906E53AB55CDF14E339A783C3F5F71BCEE130CD3423F19571FA0395FFD58F9B00ECDF2B3BEF56E37ECD15C9433895F7BCB8977F6B7AD0D3F25FB5FA4763D8BCBFF62AA0A33F7559BFBDCBD56BA43363D131A50575B182FC3705BE1458117BF4764BBECF29E0B9FB1B9FE67C0DF57C9919BE3C744D9068AB7123236C7625AE15DF3A94E74137C8CDBCF464E
	4263E2F19AF3AA8F6485000A665DA5CD00A1C0G3058ACE2A3253865FF0C0DE1C9B77B423262070867F39783FF0116C6CCDD9AD48DD3D2FDF4D452E5E9C2218631465C974033CFAC1977F99633BE5863C32AD2DDB2E5CD0555A548AD2A9BFC4CC30123B8C6272C57AB87A9EC908C91316ED7834720D5A4433C9172CBA60D0D607D1B82E84BB9E994D6048560B7374B669934F9BA683F9530A3EA07F7E4DC6F8A55D52AF8BCC31AB6938EF9D193D251023E5A4CF2FBAF0C5F8F60EBBD2620EC8C7AC3ADE21D9BF239
	ED1D81590747177485119B4FB4A2EFA63788DF7FEECC78FA2074F15234674BF1FD1D86F1175EE2793AE5F94C6FE4EB62397877F06358327949AD71E37FEE407710D76C617ABF2DFFA46A2BE3AA7AAA974E7EBA60F82B30F12ADC45E336CFB22808520FF588B06A0FD91C0F3DFF9AF9154E810D8F5090D57D704330E264016912D87CCEEA5158FA0849C3906F7D90E0C6D43EFE9AE3D2B2CDDD1E881BF4041A262CCE457A3F4176B2429321D82A083DB22611CF34F915558BAEFBFC3A1C65012AD2F66868942B7FC9
	962BD421C1B8F43F96D6CDAEFFEA867DBDC3972CDC797E4CDBAC5615B0669D2FE7D952A154C11703E08ACCDD12540AF11C9D2D4708D610E1DD76C29BE895B1B9DFADE2326676E6127DC948990F8B57BD76C5674ED69ED7392D311864E75A2244BFFB964B99CBEE70333E7D890E3517D46E7FA18D6585F86E365DA56EB6617F266089DB0ED90E6F5794051BB1EA5F93EA997A034E06A9D093A67315284D2A5472E5FD23167DF83FDBC57BA9B69E60E7FD2D0155DB3CA7CCCBF3D2245918079870F8BE9A1660B47C9E
	50D803BA62788BED51FBC0BD6A92016FD4C0C61B30750B25629E10B613C172CA7738925EE95A2CBBCDDB4C5639CF4E3B382D9E8E611EB56D3BCBBA5A051DB629F6CE3BE8981ACFE6F4EA69F8DD337B024BEEEAFF06F6D2568DF39012A5A26C71CF8D4F0BFCEB09AD9EE829D9DEEFAF37473866D67BCA5E31EAA533C728FDC52DE4722476953532DE544AD468BB50AEB8784733680F31D3F0778124G9E856CG641F92F6F8C5BBC55628F103AA149ED5D2C616A42F28B5A1974FB76B7B6B21A211EF50727A708173
	B96900DA0BE03223F5A86B026F618E414D4F54E36E1A516F1D6CBBAD7ABDDC3F1CD76F9438769A5C7D1977D80D6B390DF2E793736E4B3371F9F75FECFC5E7D7D6CD1AE07B56C3790517F475955F9794D59F83C6C62F1DD73960B6B85ADECC27E1CC63F19B502A33701ADF781DE879CBE4D6D4AF9605B73488F13F0E9012DC6C254C748991C6B2E93F395981F8458G48866481781EDBDA703C0C6F86A63CBA250CBB5AB06E99C77F295E4E51174F70BBC79234E9G1E8F69B5629D1389327CB3629D734BE654B94A
	CF4D523C697174E94811C3DCC94ED179ACDE59BB28D3F0D4F9AD2E6905EF85105CA9B8AAD2A7B8AA731B9BC9CEC9FC0E4A737E5B195E49ED145D996328B4F82B70BD2F5509BD8B203F238BE50BF5954B626A5770DFB326926BFFE2C9BF5A2D01587B73167463F977B86D7D6575767EA25BFC2E5B8B35C2370297320872D4F242B765E29757E9035526662FF72CFB73360B5B7D076D8DE4657B51FAB8DA6FC51B3309F8B33639451BF1EF177043038D68073233D073G974E8AEE583802EB57821F17F51773765D71
	BC4E6179AC0E464EF28EF7C4653FE5F23FCD4EE379F82D08659074651C955C7E2C01F95065017B89C081A4839E85E4FB843727692DE48DEE77EA21A1A0A245C69DDC3F113A3BFB27BEFBD0444C569FECA15B6E9FF09ECA90B3B99E9EAB872C567D72FA8736DCBFEF98752B69869F837C00C5C0AAA023DB68172BB7F17DAE79E57322E9CA3EG2B56C239D1194E4744C8C4DC37B36E55F45B5E1D28AE9731B7692E6E653EBB4264395D4BFD77C803705D2B50B754A3E275D735A49AC72F59FE3FD37BD9EB4F5AF15B
	F9CF7760FEF95EC8DC5FDEB46257371F9A716B5BCB466A756B95A3713B62B7067DDD41636AABF348F9D9600B9F8176838AFB617D54ABE26A3679BE0F29B871F449BFC57BB5761AB723F73FF0E2DD7D61357F86908C631DF2C2B3CDAD086B8B3991C6FF81BFB2A117C542E8A3DD1E8A18FC28A57FCF98F9D22113CC4C6FD7D75D62FE5551AB626ED0E3AC56AAEC3F3F56F06F636EFB77F48FEE070DF309FD31F9EEEDDFFCE70E5D7941065E059065322767708E24CDCE9AB46A736DF3E5D15920B12C99B2BE9CBE57
	0BF6773B2B2CB30D772E64915312717A166296EF21DD3D627EF33B81776B27F867961CBACF8527D6FEEB9329FC770293F3A29E27961F636BF71C0B5DB1E2BE7830414EFB819833F8EE753FFD701C690820CE29B0B693308B70AA20GD0F19E6AB06042F911BF57665D500ECA53D635C1EA56F416C1A06417AD16C33AA42BA60F55FFB5209DEE419A3FF008B7030F49CC10FD41A56CFEBE12583F9FC456766F47117839F6A332B2577E91091FEB6391FB2EF1993556C55D2EC941683AB3913CAF717B73D5C905DB0E
	CE449DD92A435AA02B90BA12F2D3761B81AE2FE4F3AC79E50A7B4377FF81D0CB87880C938EE9D489GG1098GGD0CB818294G94G88G88GC5FBB0B60C938EE9D489GG1098GG8CGGGGGGGGGGGGGGGGGE2F5E9ECE4E5F2A0E4E1F4E1D0CB8586GGGG81G81GBAGGG0E89GGGG
**end of data**/
}
}
