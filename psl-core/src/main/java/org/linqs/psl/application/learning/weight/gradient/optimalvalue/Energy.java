/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2023 The Regents of the University of California
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.linqs.psl.application.learning.weight.gradient.optimalvalue;

import org.linqs.psl.database.Database;
import org.linqs.psl.model.rule.Rule;

import java.util.List;

/**
 * Learns new weights for the weighted rules in a model by minimizing the value of the energy function
 * of the optimal latent inference problem.
 * In the case there is no latent variables, this reduces to minimizing the energy the observations.
 */
public class Energy extends OptimalValue {
    public Energy(List<Rule> rules, Database rvDB, Database observedDB, Database validationDB) {
        super(rules, rvDB, observedDB, validationDB);
    }

    @Override
    protected float computeLearningLoss() {
        return super.computeEnergyLoss();
    }

    @Override
    protected void computeIterationStatistics() {
        computeLatentStatistics();
    }

    @Override
    protected void addLearningLossWeightGradient() {
        super.addEnergyLossWeightGradient(1.0f);
    }

    @Override
    protected void addLearningLossAtomGradient() {
        super.addEnergyLossAtomGradient(1.0f);
    }
}
