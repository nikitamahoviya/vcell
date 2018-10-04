package org.vcell.model.rbm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vcell.util.Compare;
import org.vcell.util.Issue;
import org.vcell.util.IssueContext;
import org.vcell.util.Matchable;

import cbit.vcell.mapping.NetworkTransformer;

@SuppressWarnings("serial")
public class NetworkConstraints extends RbmElementAbstract implements Matchable, Serializable {
	public static final String PROPERTY_NAME_MAX_STOICHIOMETRY = "maxStoichiometry";
	public static final String PROPERTY_NAME_MAX_ITERATION = "maxIteration";
	public static final String PROPERTY_NAME_MOLECULES_PER_SPECIES = "maxMoleculesPerSpecies";
	public static final String PROPERTY_NAME_SPECIES_LIMIT = "speciesLimit";
	public static final String PROPERTY_NAME_REACTIONS_LIMIT = "reactionsLimit";
	
	public static final String SPECIES_LIMIT_PARAMETER = "vcellReservedParameter_speciesLimit";
	public static final String REACTIONS_LIMIT_PARAMETER = "vcellReservedParameter_reactionsLimit";
	
	private int maxIteration = NetworkTransformer.defaultMaxIteration;
	private int maxMoleculesPerSpecies = NetworkTransformer.defaultMaxMoleculesPerSpecies;
	private int speciesLimit = NetworkTransformer.defaultSpeciesLimit;
	private int reactionsLimit = NetworkTransformer.defaultReactionsLimit;
	
	private Map<MolecularType, Integer> maxStoichiometryMap = new HashMap<MolecularType, Integer>();
	
	private transient int testMaxIteration = maxIteration;
	private transient int testMaxMoleculesPerSpecies = maxMoleculesPerSpecies;
	private transient int testSpeciesLimit = speciesLimit;
	private int testReactionsLimit = reactionsLimit;

	public NetworkConstraints() {		
	}
	public NetworkConstraints(NetworkConstraints that) {
		this();
		this.maxIteration = that.maxIteration;
		this.maxMoleculesPerSpecies = that.maxMoleculesPerSpecies;
		this.speciesLimit = that.speciesLimit;
		this.reactionsLimit = that.reactionsLimit;
		this.testMaxIteration = that.testMaxIteration;
		this.testMaxMoleculesPerSpecies = that.testMaxMoleculesPerSpecies;
		this.testSpeciesLimit = that.testSpeciesLimit;
		this.testReactionsLimit = that.testReactionsLimit;
		
		for (Map.Entry<MolecularType, Integer> entry : that.maxStoichiometryMap.entrySet()) {
			MolecularType key = entry.getKey();		// use the same instances of the molecular type 
			int value = entry.getValue();
			this.maxStoichiometryMap.put(key, value);
		}
	}
	
	public void setTestConstraints(int testMaxIteration, int testMaxMoleculesPerSpecies, int testSpeciesLimit, int testReactionsLimit) {
		this.testMaxIteration = testMaxIteration;
		this.testMaxMoleculesPerSpecies = testMaxMoleculesPerSpecies;
		this.testSpeciesLimit = testSpeciesLimit;
		this.testReactionsLimit = testReactionsLimit;
	}
	public void updateConstraintsFromTest() {
		setMaxIteration(testMaxIteration);
		setMaxMoleculesPerSpecies(testMaxMoleculesPerSpecies);
		setSpeciesLimit(testSpeciesLimit);
		setReactionsLimit(testReactionsLimit);
	}
	public boolean isTestConstraintsDifferent() {
		if(testMaxIteration != maxIteration || testMaxMoleculesPerSpecies != maxMoleculesPerSpecies || testSpeciesLimit != speciesLimit || testReactionsLimit != reactionsLimit) {
			return true;
		}
		return false;
	}
	public final int getMaxIteration() {
		return maxIteration;
	}
	public final void setMaxIteration(int newValue) {
		int oldValue = maxIteration;
		this.maxIteration = newValue;
		firePropertyChange(PROPERTY_NAME_MAX_ITERATION, oldValue, newValue);
	}

	public final int getMaxMoleculesPerSpecies() {
		return maxMoleculesPerSpecies;
	}
	public final void setMaxMoleculesPerSpecies(int newValue) {
		int oldValue = maxMoleculesPerSpecies;
		this.maxMoleculesPerSpecies = newValue;
		firePropertyChange(PROPERTY_NAME_MOLECULES_PER_SPECIES, oldValue, newValue);
	}
	
	public final int getSpeciesLimit() {
		return speciesLimit;
	}
	public final void setSpeciesLimit(Integer newValue) {
		int oldValue = speciesLimit;
		this.speciesLimit = newValue;
		firePropertyChange(PROPERTY_NAME_SPECIES_LIMIT, oldValue, newValue);
	}
	public final int getReactionsLimit() {
		return reactionsLimit;
	}
	public final void setReactionsLimit(Integer newValue) {
		int oldValue = reactionsLimit;
		this.reactionsLimit = newValue;
		firePropertyChange(PROPERTY_NAME_REACTIONS_LIMIT, oldValue, newValue);
	}
	
	public void setMaxStoichiometry(MolecularType molecularType, Integer newValue) {
		Integer oldValue;
		if (newValue == null) {
			oldValue = maxStoichiometryMap.remove(molecularType);
		} else {
			oldValue = maxStoichiometryMap.get(molecularType);
			maxStoichiometryMap.put(molecularType, newValue);
		}
		firePropertyChange(PROPERTY_NAME_MAX_STOICHIOMETRY, oldValue, newValue);
	}
	
	public final int getTestMaxIteration() {
		return testMaxIteration;
	}
	public final int getTestMaxMoleculesPerSpecies() {
		return testMaxMoleculesPerSpecies;
	}
	public final int getTestSpeciesLimit() {
		return testSpeciesLimit;
	}
	public final int getTestReactionsLimit() {
		return testReactionsLimit;
	}

	public Map<MolecularType, Integer> getMaxStoichiometry() {
		return maxStoichiometryMap;
	}
	public Integer getMaxStoichiometry(MolecularType molecularType) {
		return maxStoichiometryMap.get(molecularType);
	}
	
	public void clear() {
		maxStoichiometryMap.clear();
		maxIteration = NetworkTransformer.defaultMaxIteration;
		maxMoleculesPerSpecies = NetworkTransformer.defaultMaxMoleculesPerSpecies;
		speciesLimit = NetworkTransformer.defaultSpeciesLimit;
		reactionsLimit = NetworkTransformer.defaultReactionsLimit;
	}
	
	@Override
	public boolean compareEqual(Matchable aThat) {
		if (this == aThat) {
			return true;
		}
		if (!(aThat instanceof NetworkConstraints)) {
			return false;
		}
		NetworkConstraints that = (NetworkConstraints)aThat;

		if (!Compare.isEqual(maxIteration, that.maxIteration)) {
			return false;
		}
		if (!Compare.isEqual(maxMoleculesPerSpecies, that.maxMoleculesPerSpecies)) {
			return false;
		}
		if (!Compare.isEqual(speciesLimit, that.speciesLimit)) {
			return false;
		}
		if (!Compare.isEqual(reactionsLimit, that.reactionsLimit)) {
			return false;
		}
		Map<MolecularType, Integer> thatMaxStoichiometryMap = new HashMap<MolecularType, Integer>(maxStoichiometryMap);
		for(Map.Entry<MolecularType, Integer> var1 : maxStoichiometryMap.entrySet()) {
			boolean found = false;
			for(Map.Entry<MolecularType, Integer> var2 : thatMaxStoichiometryMap.entrySet()) {
				if(Compare.isEqual(var1.getKey(),var2.getKey()) && Compare.isEqual(var1.getValue(),var2.getValue())) {
					found = true;
					thatMaxStoichiometryMap.remove(var2);
					break;
				}
			}
			if(found == false) {
				return false;
			}
		}
		if(!thatMaxStoichiometryMap.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public void gatherIssues(IssueContext issueContext, List<Issue> issueList) {
		// TODO Auto-generated method stub
	}

}
