#!/usr/bin/perl
use strict;
use warnings;

use List::Util "any";

my $file = "input.txt";
# Open file and read contents into array
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";


# =========== Solve the puzzle ============
my @test = (-6, +3, +8, +5, -6);
my $part1 = find_frequency_sum(@lines);
print "Part 1: $part1\n";
my $part2 = get_repeated_frequency(@lines);
print "Part 2: $part2\n";

# =========== Subroutine declarations ============
sub find_frequency_sum {
  my @frequencies = @_;

  my $sum = 0;
  foreach my $freq (@frequencies){
    $sum += $freq;
  }
  return $sum
}

sub get_repeated_frequency {
  my @frequencies = @_;

  my @freqs = (0);
  my $num_list_runs = 0;
  while (1) {
    # Loop through list
    foreach my $freq (@frequencies){
      my $new_freq = $freqs[-1] + $freq;
      # Check if the new frequency is in the frequency list, return if it is.
      if (any { $_ eq $new_freq } @freqs) {
        return $new_freq;
      }
      # Append the new frequency to the list of known freqs
      push @freqs, $new_freq;
    }
    $num_list_runs += 1;
    print "List runs: $num_list_runs\n";
  }
}# END subroutine get_repeated_frequency
