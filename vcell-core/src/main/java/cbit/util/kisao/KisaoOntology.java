package cbit.util.kisao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cbit.vcell.solver.SolverDescription;
import cbit.vcell.solver.SolverUtilities;;


public class KisaoOntology {
	
	private List<KisaoTerm> terms = new ArrayList<>();
	private static KisaoOntology instance;
	
	
	public static KisaoOntology getInstance() {
		if (instance == null) {
			instance = new KisaoTermParser().parse();
		}
		return instance;
	}
	
	public void addTerm(KisaoTerm curr) {
		terms.add(curr);
	}
	public List<KisaoTerm> getTerms() {
		return Collections.unmodifiableList(terms);
	}

	public KisaoTerm getTermById(String id) {
		for (KisaoTerm term : terms) {
			if (term.getId().equalsIgnoreCase(id)) {
				return term;
			}
		}
		return null;
	}
	
	public void createRelations() {
		for (KisaoTerm term : terms) {
			List<String> isas = term.getIsaRef();
			for (String isa : isas) {
				KisaoTerm termIsa = getTermById(isa);
				if (termIsa != null) {
					term.addIsa(termIsa);
				}
			}
		}
	}
	
	public static List<KisaoTerm> makeDescendantList(KisaoTerm root) {
		
		List<KisaoTerm> descendantList = new ArrayList<>();
		if(root.getId().equals("KISAO_0000000")) {
			return descendantList;		// for KISAO_0000000 we have no descendants
		}
		
		while(true) {
			List<KisaoTerm> tmpList = root.getIsa();
			if(tmpList.size() > 1) {
				throw new RuntimeException("Each kisao term must have no more than 1 descendant");
			}
			if(tmpList.size() == 0) {	// we should never get here
				if(root.getId().equals("KISAO_0000000")) {
					throw new RuntimeException("Error reaching KISAO_0000000");
				} else {
					throw new RuntimeException("Malformed entry for " + root.getId() + ", is_a term missing");
				}
			}
			KisaoTerm kt = tmpList.get(0);
			if(kt.getId().equals("KISAO_0000000")) {
				return descendantList;	// finished going through all the descendants
			}
			descendantList.add(kt);
			root = kt;
		}
	}
	
	public static void main(String[] args) {
		
		List<KisaoTerm> terms = KisaoOntology.getInstance().getTerms();
		
		for(KisaoTerm term : terms) {
			System.out.println("-- current kisao term is: " + term);
			System.out.println("Descendant List");
			List<KisaoTerm> descendantList = KisaoOntology.makeDescendantList(term);
			for(KisaoTerm descendant : descendantList) {
				System.out.println("   " + descendant);
			}
			
			System.out.println("Trying to match with VCell solver");
			SolverDescription sd = SolverUtilities.matchSolverWithKisaoId(term.getId());
			if(sd != null) {
				System.out.println("   - matched with vcell solver: " + sd.getKisao() + ", " + sd.getDisplayLabel());
			} else {
				System.out.println("   - didn't match");
			}
			System.out.println(" ------------------------------------------------------ ");
		}
		System.out.println("Done");

	}

}
