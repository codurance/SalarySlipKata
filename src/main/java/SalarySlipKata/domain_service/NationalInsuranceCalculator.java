package SalarySlipKata.domain_service;

import static java.lang.Double.MAX_VALUE;
import static SalarySlipKata.domain.Money.zero;

import java.util.ArrayList;
import java.util.List;

import SalarySlipKata.domain.Money;
import SalarySlipKata.domain.bands.Band;
import SalarySlipKata.domain.bands.StandardBand;

public class NationalInsuranceCalculator {

  private Band higherContributions = new StandardBand(new Money(43_000.00), new Money(MAX_VALUE), 0.02);
  private Band basicContributions = new StandardBand(new Money( 8_060.00), new Money(43_000.00), 0.12);
  private Band noContributions = new StandardBand(new Money(     0.00), new Money(8_060.00 ), 0.00);

  private List<Band> niContributionBands = new ArrayList<Band>() {
    { add(higherContributions); }
    { add(basicContributions);  }
    { add(noContributions);     }
  };

  public Money calculateContributionsFor(Money annualSalary) {
    Money contributions = zero();

    for(Band niContributionBand : niContributionBands) {
      final Money contributionForTheBand = niContributionBand.calculateFrom(annualSalary);
      contributions = contributions.plus(contributionForTheBand);
    }

    return contributions;
  }
}
